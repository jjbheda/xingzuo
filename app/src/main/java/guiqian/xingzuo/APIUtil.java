/**
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2015 Carlos Piñan
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package guiqian.xingzuo;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Carlos Piñan
 */
public class APIUtil {

    public static final String ENDPOINT_URL = "http://api.avatardata.cn/Constellation/";

    private static APIUtil instance;

    private APIService service;

    private APIUtil() {
        /* IGNORED */
    }

    public static APIUtil get() {
        if (instance == null) {
            instance = new APIUtil();
        }
        return instance;
    }

    public APIService getRetrofitService() {
        if (service == null) {
            OkHttpClient client = new OkHttpClient();
//            client.interceptors().add(new LoggingInterceptor());
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            service = retrofit.create(APIService.class);
        }
        return service;
    }

}
