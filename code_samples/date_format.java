

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class DateFormats {

  public static final String yyyyMMdd_DASH = "yyyy-MM-dd";

  public static final String MMDDYYYY_SLASH = "MM/dd/yyyy";

  public static final String ASIA_KOLKATTA_ZONE = "Asia/Kolkata";

  public static final DateTimeFormatter YYYYMMDD_DASH_FORMAT =
      new DateTimeFormatterBuilder()
          .appendPattern(yyyyMMdd_DASH)
          .toFormatter(Locale.ENGLISH);

  public static final DateTimeFormatter DDMMMYYYY_DASH_FORMAT =
      new DateTimeFormatterBuilder()
          .appendPattern("dd-MMM-yyyy")
          .toFormatter(Locale.ENGLISH);

  public static final DateTimeFormatter DDMMMYY_DASH_FORMAT =
      new DateTimeFormatterBuilder()
          .parseCaseInsensitive()
          .appendPattern("dd-MMM-yy")
          .toFormatter(Locale.ENGLISH);

  public static final DateTimeFormatter DDMMYYYY_FORMAT =
      new DateTimeFormatterBuilder()
          .appendPattern("ddMMyyyy")
          .toFormatter(Locale.ENGLISH);

  public static final DateTimeFormatter HHMMSS_FORMAT =
      new DateTimeFormatterBuilder()
          .appendPattern("HH:mm:ss")
          .toFormatter(Locale.ENGLISH);

  public static final DateTimeFormatter MMMDYYYYHHMMSS_FORMAT_OLD =
      new DateTimeFormatterBuilder()
          .appendPattern("MMM d, yyyy h:mm:ss a")
          .toFormatter(Locale.ENGLISH);

  public static final DateTimeFormatter MMMDYYYYHHMMSS_FORMAT =
      new DateTimeFormatterBuilder()
          .appendPattern("MMM d, yyyy, h:mm:ss a")
          .toFormatter(Locale.ENGLISH);

  public static final DateTimeFormatter MMMDDYYYY_FORMAT =
      new DateTimeFormatterBuilder()
          .appendPattern("MMM dd, yyyy")
          .toFormatter(Locale.ENGLISH);

  public static final DateTimeFormatter YYYYMMDDHHMMSS_FORMAT =
      new DateTimeFormatterBuilder()
          .appendPattern("yyyyMMddHHmmss")
          .toFormatter(Locale.ENGLISH);

  public static final DateTimeFormatter HHMMSS_SHORT_FORMAT =
      new DateTimeFormatterBuilder()
          .appendPattern("HHmmss")
          .toFormatter(Locale.ENGLISH);

  public static final DateTimeFormatter DDMMYYYY_SLASH_FORMAT =
      new DateTimeFormatterBuilder()
          .parseCaseInsensitive()
          .appendPattern("dd/MM/yyyy")
          .toFormatter(Locale.ENGLISH);

  public static final DateTimeFormatter MMDDYYYY_SLASH_FORMATTER =
      new DateTimeFormatterBuilder()
          .appendPattern(MMDDYYYY_SLASH)
          .toFormatter(Locale.ENGLISH);

  public static DateTimeFormatter getFormatterFor(String format) {
    return new DateTimeFormatterBuilder()
        .appendPattern(format)
        .toFormatter(Locale.ENGLISH);
  }

  /**
   * formatter output: 2019-04-03T12:18:53+05:30
   */
  public static final DateTimeFormatter ISO_DATE_FOR_ASIA_ZONE = new DateTimeFormatterBuilder()
      .appendPattern("yyyy-MM-dd'T'HH:mm:ssxxx")
      .toFormatter(Locale.ENGLISH)
      .withZone(ZoneId.of(ASIA_KOLKATTA_ZONE));

}
