package guiqian.xingzuo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jiangjingbo on 2017/9/9.
 */

public class HTMLSpirit{
    public static String delHTMLTag(String htmlStr){
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式
        String regEx_img="<img*>";
        Pattern p_script= Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
        Matcher m_script=p_script.matcher(htmlStr);
        htmlStr=m_script.replaceAll(""); //过滤script标签

        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
        Matcher m_style=p_style.matcher(htmlStr);
        htmlStr=m_style.replaceAll(""); //过滤style标签

        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(htmlStr);
        htmlStr=m_html.replaceAll(""); //过滤html标签

        Pattern img_html=Pattern.compile(regEx_img,Pattern.CASE_INSENSITIVE);
        Matcher mm_html=img_html.matcher(htmlStr);
        htmlStr=mm_html.replaceAll(""); //过滤html标签

        return htmlStr.trim(); //返回文本字符串
    }
}