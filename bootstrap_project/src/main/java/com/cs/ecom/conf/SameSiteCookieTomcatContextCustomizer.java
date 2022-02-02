package com.cs.ecom.conf;

import org.apache.catalina.Context;
import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;

//@Component
public class SameSiteCookieTomcatContextCustomizer implements TomcatContextCustomizer {

    @Override
    public void customize(final Context context) {
        final Rfc6265CookieProcessor cookieProcessor = new Rfc6265CookieProcessor();
        cookieProcessor.setSameSiteCookies(SameSiteCookies.NONE.getValue());
        context.setCookieProcessor(cookieProcessor);
    }
}