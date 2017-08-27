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

import guiqian.xingzuo.model.Destination;
import guiqian.xingzuo.model.Dream;
import guiqian.xingzuo.model.PairResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static android.R.attr.key;

/**
 * @author Carlos Piñan
 */
public interface APIService {

    public static String XingzuoKey = "efa739632f4a49bda66ed726a55490af";
    public static String StarPairKey = "b80c49a2d846467888c6a8a5744e4074";

    @GET("Constellation/Query")
    Call<Destination> getDestination(
            @Query("key") String key,
            @Query("consName") String consName,
            @Query("type") String type,
            @Query("dtype") String dtype,
            @Query("format") boolean format
    );

    @GET("ZhouGongJieMeng/LookUp")
    Call<Dream> getDreamParse(
            @Query("key") String key,
            @Query("keyword") String keyword

    );

    @GET("XingZuoPeiDui/LookUp")
    Call<PairResult> starPair(
            @Query("key") String key,
            @Query("xingzuo1") String xingzuo1,
            @Query("xingzuo2") String xingzuo2

    );

}
