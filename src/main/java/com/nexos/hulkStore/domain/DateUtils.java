/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.hulkstore.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 *
 * @author Carlos Montealegre
 */
public class DateUtils {

    public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String TIME_ZONE_BOGOTA = "America/Bogota";
    public static final String TIME_ZONE_GMT = "GMT";

    /**
     * ISO 8601 parser
     */
    protected final SimpleDateFormat iso8601DateParser
            = new SimpleDateFormat(SIMPLE_DATE_FORMAT);
    /**
     * Alternate ISO 8601 parser without fractional seconds
     */
    protected final SimpleDateFormat alternateIso8601DateParser
            = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    /**
     * Alternate ISO 8601 parser with fractional seconds
     */
    protected final SimpleDateFormat iso8601DateParserNoFractionalSeconds
            = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    /**
     * Alternate ISO 8601 parser with Time Zone
     */
    protected final SimpleDateFormat iso8601DateParserTimeZone
            = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    /**
     * Alternate ISO 8601 parser with Time Zone and Fractional Seconds
     */
    protected final SimpleDateFormat iso8601DateParserTimeZoneFractionalSeconds
            = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    /**
     * RFC 822 parser
     */
    protected final SimpleDateFormat rfc822DateParser
            = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
    /**
     * Simple Date parser
     */
    protected final SimpleDateFormat simpleDateParser
            = new SimpleDateFormat(SIMPLE_DATE_FORMAT);
    /**
     * Simple Time parser
     */
    protected final SimpleDateFormat simpleTimeParser
            = new SimpleDateFormat("HH:mm z", Locale.US);

    /**
     * Simple Time parser
     */
    protected final SimpleDateFormat simpleTimeParserWithOutTimeZone
            = new SimpleDateFormat("HH:mm", Locale.US);

    /**
     * UBL Date Format
     */
    protected final SimpleDateFormat ublDateFormat
            = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * UBL Date Format
     */
    protected final SimpleDateFormat ublDateFormatDDMMAAAA
            = new SimpleDateFormat("ddMMyyyy");

    /**
     * Standard Date Format
     */
    protected final SimpleDateFormat standardDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    protected final SimpleDateFormat standardDateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    protected final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HHmmss");

    /**
     * Constructs a new DateUtils object, ready to parse/format dates.
     */
    public DateUtils() {
        iso8601DateParser.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_BOGOTA));
        rfc822DateParser.setTimeZone(new SimpleTimeZone(5, TIME_ZONE_GMT));
        alternateIso8601DateParser.setTimeZone(new SimpleTimeZone(0, TIME_ZONE_GMT));
        simpleDateParser.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_BOGOTA));
        simpleTimeParser.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_BOGOTA));
    }

    /**
     * Parses the specified date string as an ISO 8601 date and returns the Date
     * object.
     *
     * @param dateString The date string to parse.
     *
     * @return The parsed Date object.
     *
     * @throws ParseException If the date string could not be parsed.
     */
    public Date parseIso8601Date(String dateString) throws ParseException {
        try {
            synchronized (iso8601DateParser) {
                return iso8601DateParser.parse(dateString);
            }
        } catch (ParseException parserIsoException) {
            // If the first ISO 8601 parser didn't work, try the alternate
            // version which doesn't include fractional seconds
            try {
                synchronized (alternateIso8601DateParser) {
                    return alternateIso8601DateParser.parse(dateString);
                }
            } catch (ParseException parserAlternateException) {
                // If the first ISO 8601 parser didn't work, try the alternate
                // version which doesn't include fractional seconds and time Zone
                try {
                    synchronized (iso8601DateParserNoFractionalSeconds) {
                        return iso8601DateParserNoFractionalSeconds.parse(dateString);
                    }
                } catch (ParseException parserNoFractional) {
                    synchronized (iso8601DateParserTimeZone) {
                        return iso8601DateParserTimeZone.parse(dateString);
                    }
                }
            }
        }
    }

    /**
     * Formats the specified date as an ISO 8601 string.
     *
     * @param date The date to format.
     *
     * @return The ISO 8601 string representing the specified date.
     */
    public String formatIso8601Date(Date date) {
        synchronized (iso8601DateParser) {
            return iso8601DateParser.format(date);
        }
    }

    /**
     * Parses the specified date string as an RFC 822 date and returns the Date
     * object.
     *
     * @param dateString The date string to parse.
     *
     * @return The parsed Date object.
     *
     * @throws ParseException If the date string could not be parsed.
     */
    public Date parseRfc822Date(String dateString) throws ParseException {
        synchronized (rfc822DateParser) {
            return rfc822DateParser.parse(dateString);
        }
    }

    /**
     * Formats the specified date as an RFC 822 string.
     *
     * @param date The date to format.
     *
     * @return The RFC 822 string representing the specified date.
     */
    public String formatRfc822Date(Date date) {
        synchronized (rfc822DateParser) {
            return rfc822DateParser.format(date);
        }
    }

    /**
     * Parses the specified date string as a date and returns the Date object.
     * Format String: yyyy-MM-dd HH:mm:ss.SSS z
     *
     * @param dateString The date string to parse.
     *
     * @return The parsed Date object.
     *
     * @throws ParseException If the date string could not be parsed.
     */
    public Date parseSimpleDate(String dateString) throws ParseException {
        synchronized (simpleDateParser) {
            return simpleDateParser.parse(dateString);
        }
    }

    /**
     * Formats the specified date as string with format: yyyy-MM-dd HH:mm:ss.SSS
     * z
     *
     * @param date The date to format.
     *
     * @return The RFC 822 string representing the specified date.
     */
    public String formatSimpleDate(Date date) {
        synchronized (simpleDateParser) {
            return simpleDateParser.format(date);
        }
    }

    /**
     * Parses the specified time string as a date and returns the Date object.
     * Format String: HH:mm z
     *
     * @param dateString The date string to parse.
     *
     * @return The parsed Date object.
     *
     * @throws ParseException If the date string could not be parsed.
     */
    public Date parseSimpleTime(String dateString) throws ParseException {
        synchronized (simpleTimeParser) {
            return simpleTimeParser.parse(dateString);
        }
    }

    /**
     * Formats the specified time as string with format: HH:mm:ss z
     *
     * @param date The date to format.
     *
     * @return The RFC 822 string representing the specified date.
     */
    public String formatSimpleTime(Date date) {
        synchronized (simpleTimeParser) {
            return simpleTimeParser.format(date);
        }
    }

    /**
     * Formats the specified time as string with format: HH:mm:ss
     *
     * @param date The date to format.
     *
     * @return The RFC 822 string representing the specified date.
     */
    public String formatSimpleTimeWithOutTimeZone(Date date) {
        synchronized (simpleTimeParserWithOutTimeZone) {
            return simpleTimeParserWithOutTimeZone.format(date);
        }
    }

    public String formatIso8601DateParserTimeZoneFractionalSeconds(Date date) {
        synchronized (iso8601DateParserTimeZoneFractionalSeconds) {
            return iso8601DateParserTimeZoneFractionalSeconds.format(date);
        }
    }

    public Date addDays(Date initialDate, int days) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(initialDate);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }

    public Date setTime(Date initialDate, int hours, int minutes, int seconds) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(initialDate);
        cal.set(Calendar.HOUR_OF_DAY, hours);
        cal.set(Calendar.MINUTE, minutes);
        cal.set(Calendar.SECOND, seconds);

        return cal.getTime();
    }

    public Date convertUblDateStringToDate(String stringDateTime) throws ParseException {
        synchronized (ublDateFormat) {
            return ublDateFormat.parse(stringDateTime);
        }
    }

    /**
     * Formats the specified date as string with format: DDMMAAAA
     *
     * @param date The date to format.
     *
     * @return The RFC 822 string representing the specified date.
     */
    public String formatSimpleDateDDMMMAAAA(Date date) {
        synchronized (ublDateFormatDDMMAAAA) {
            return ublDateFormatDDMMAAAA.format(date);
        }
    }

    public Date parseSimpleDateDDMMMAAAA(String dateString) throws ParseException {
        synchronized (ublDateFormatDDMMAAAA) {
            return ublDateFormatDDMMAAAA.parse(dateString);
        }
    }

    public Date convertStandardFormatDateStringToDate(String stringDateTime) throws ParseException {
        return standardDateFormat.parse(stringDateTime);
    }

    public Date convertStandardFormatDateTimeStringToDate(String stringDateTime) throws ParseException {
        return standardDateTimeFormat.parse(stringDateTime);
    }

    public Date convertStringToDateTime(String stringDateTime) throws ParseException {
        return dateTimeFormat.parse(stringDateTime);
    }
}
