package spring2go.io.authcodeapp.client.interceptor;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Interceptor that adds Bearer prefix to an access token
 */
public class BearerTokenHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        List<String> headers = request.headers("Authorization");
        if (headers.size() > 0) {
            String accessTokenValue = headers.get(0);

            request = request.newBuilder()
                .removeHeader("Authorization")
                .addHeader("Authorization", "Bearer " + accessTokenValue)
                .build();

        }

        return chain.proceed(request);

    }
}
