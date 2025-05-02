package kr.or.ddit.calendar;

import java.time.Month;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class UIMetaDatas{

	public UIMetaDatas() {
		months = Arrays
					.stream(Month.values())
					.map(m -> m.getDisplayName(TextStyle.FULL, Locale.KOREAN))
					.toArray(String[]::new);
		
		locales = Arrays
					.stream(Locale.getAvailableLocales())
					.collect(Collectors.toMap(
							l->l.toLanguageTag(), 
							l->l.getDisplayLanguage(l),
							(v1, v2)->v1
						)
					);
		
		zoneIds = ZoneId.getAvailableZoneIds()
					.stream()
					.map(TimeZone::getTimeZone)
					.collect(
						Collectors.toMap(
							tz->tz.getID(),
							tz->tz.getDisplayName())
					);
		
		
	}
	
	private String[] months;
	private Map<String, String> locales;
	private Map<String, String> zoneIds;
	
}
