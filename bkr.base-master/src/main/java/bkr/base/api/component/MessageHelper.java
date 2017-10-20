package bkr.base.api.component;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import bkr.base.api.result.Message;

@Component
public class MessageHelper {

	@Autowired
	private MessageSource messageSource;

	public String getMessage(Message message) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(message.value(), null, locale);
	}
}
