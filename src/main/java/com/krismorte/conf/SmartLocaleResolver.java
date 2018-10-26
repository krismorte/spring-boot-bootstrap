package com.krismorte.conf;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import com.krismorte.ApplicationConstants;

//https://stackoverflow.com/questions/36655104/spring-boot-localization-issue-accept-language-header
public class SmartLocaleResolver extends AcceptHeaderLocaleResolver {

    @Override
       public Locale resolveLocale(HttpServletRequest request) {
               if (StringUtils.isBlank(request.getHeader("Accept-Language"))) {
               return Locale.getDefault();
               }
               List<Locale.LanguageRange> list = Locale.LanguageRange.parse(request.getHeader("Accept-Language"));
               Locale locale = Locale.lookup(list, ApplicationConstants.LOCALES);
               return locale ;
       }
}
