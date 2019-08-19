package com.sample.yelp.fair.network;

import com.sample.yelp.fair.BuildConfig;
import com.sample.yelp.fair.utils.ApiConstants;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.cert.CertificateException;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * A class used to perform the REST commands for POST, PUT, GET, and DELETE for API calls.
 * This is a direct implementation that utilizes Retrofit. If in the future a new 3rd party restful
 * based library is used start with changing this class and the {@link } interface.
 *
 * @author ernestholloway on 10/11/16.
 */
public class RestClientManager {
    private static RestClientManager restClientManager;

    private RetrofitInterface apiClient;

    private RestClientManager() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder()
                        .addHeader(ApiConstants.AUTHORIZATION, "Bearer "+ApiConstants.API_KEY);
                return chain.proceed(builder.build());
            }
        };
        OkHttpClient.Builder httpClient2 = getUnsafeOkHttpClient();
        httpClient2.addNetworkInterceptor(interceptor);
        httpClient2.readTimeout(45, SECONDS);
        httpClient2.writeTimeout(45, SECONDS);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.FLAVOR.equalsIgnoreCase(ApiConstants.ENVIRONMENT_PROD)) {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        httpClient2.addInterceptor(logging);
        OkHttpClient client = httpClient2.build();

        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiClient = retrofit.create(RetrofitInterface.class);
    }  //private constructor.

    public static RestClientManager getInstance() {
        if (restClientManager == null) { //if there is no instance available... create new one
            restClientManager = new RestClientManager();
        }

        return restClientManager;
    }

    public RetrofitInterface getApiClient() {
        return apiClient;
    }

    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
