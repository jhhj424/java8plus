package step5;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/*
자바 8 버전 이후의 Date와 Time API 예제
 */
public class App2 {

    public static void main(String[] args) {
        Instant instant = Instant.now();
        System.out.println(instant); // 기준시 UTC, GMT

        // 현재 나의 위치
        ZoneId zone = ZoneId.systemDefault();
        System.out.println(zone);
        // 위치 기준Time
        ZonedDateTime zonedDateTime = instant.atZone(zone);
        System.out.println(zonedDateTime);

        // 현재 나의 시스템 존 을 참고한 현재 시간
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        // 날짜 만들기
        LocalDateTime of = LocalDateTime.of(1996, Month.APRIL, 24, 0, 0, 0);
        System.out.println(of);

        // 존에 해당하는 현재시간 1
        ZonedDateTime now1 = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(now1);

        // 존에 해당하는 현재시간 2
        Instant now2 = Instant.now();
        ZonedDateTime zonedDateTime1 = now2.atZone(ZoneId.of("Asia/Seoul"));
        System.out.println(zonedDateTime1);

        // 서로 형변환 가능 (ZonedDateTime/Instant)
        Instant instant1 = zonedDateTime1.toInstant();


        // 날짜 사이의 일수 계산하기 - Period : 사람용
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2021,Month.APRIL,24);

        Period between = Period.between(today, thisYearBirthday);
        System.out.println(between.getDays());
        System.out.println(between.get(ChronoUnit.DAYS));

        Period until = today.until(thisYearBirthday);
        System.out.println(until);

        // 날짜 사이의 일수 계산하기 - Duration : 기계용
        Instant now3 = Instant.now();
        Instant plus = now3.plus(10, ChronoUnit.SECONDS);
        Duration between1 = Duration.between(now3, plus);
        System.out.println(between1.getSeconds());

        // 포맷팅
        LocalDateTime now4 = LocalDateTime.now();
        System.out.println(now4);
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(now4.format(MMddyyyy));

        LocalDate parse = LocalDate.parse("04/24/1996", MMddyyyy);
        System.out.println(parse);

        // Date <-> Instant
        Date date = new Date();
        Instant instant2 = date.toInstant();
        Date from = Date.from(instant2);
        System.out.println(from);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        ZonedDateTime zonedDateTime2 = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());
        GregorianCalendar from1 = GregorianCalendar.from(zonedDateTime2);
        System.out.println(from1);
    }
}
