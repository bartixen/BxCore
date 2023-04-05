package pl.bartixen.bxcore.Ban;

import pl.bartixen.bxcore.Data.BanDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MuteTimeChecker {

    Main plugin;

    static BanDataManager band;

    static String seconds;

    static String minutes;

    static String hours;

    static String days;

    static String seconds1;

    static String minutes1;

    static String hours1;

    static String days1;

    static int leftSide;

    static int rightSide;

    public MuteTimeChecker(Main m) {
        plugin = m;
        band = BanDataManager.getInstance();
    }

    public static boolean check(String player) throws IOException {
        String banDate = band.getData().getString(player + ".tempmute.data");
        banDate = banDate.replace(":", " ").replace("-", " ");
        String[] newString = banDate.split(" ");
        seconds = newString[2];
        minutes = newString[1];
        hours = newString[0];
        days = newString[3];
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        String date = format.format(now);
        date = date.replace(":", " ").replace("-", " ");
        String[] newString1 = date.split(" ");
        seconds1 = newString1[2];
        minutes1 = newString1[1];
        hours1 = newString1[0];
        days1 = newString1[3];
        String until = band.getData().getString(player + ".tempmute.czas");
        String integer1 = until.replaceAll("[^\\d.]", "");
        int timeUntilUnban = Integer.parseInt(integer1);
        if (!until.contains("s"))
            if (until.contains("m")) {
                timeUntilUnban *= 60;
            } else if (until.contains("h")) {
                timeUntilUnban = timeUntilUnban * 60 * 60;
            } else if (until.contains("d")) {
                timeUntilUnban = timeUntilUnban * 60 * 60 * 24;
            }
        int seconds2 = Integer.parseInt(seconds);
        int minutes2 = Integer.parseInt(minutes);
        int hours2 = Integer.parseInt(hours);
        int days2 = Integer.parseInt(days);
        int seconds3 = Integer.parseInt(seconds1);
        int minutes3 = Integer.parseInt(minutes1);
        int hours3 = Integer.parseInt(hours1);
        int days3 = Integer.parseInt(days1);
        leftSide = seconds2 + minutes2 * 60 + hours2 * 60 * 60 + days2 * 60 * 60 * 24 + timeUntilUnban;
        rightSide = seconds3 + minutes3 * 60 + hours3 * 60 * 60 + days3 * 60 * 60 * 24;
        if (leftSide >= rightSide)
            return true;
        band.getData().set(player + ".tempmute", null);
        band.saveData();
        return false;
    }

    public static String getTime() {
        int timer = leftSide - rightSide;
        int convertedDays = timer / 86400;
        int remainder1 = timer % 86400;
        int convertedHours = remainder1 / 3600;
        int remainder = timer % 3600;
        int convertedMinutes = remainder / 60;
        int convertedSeconds = remainder % 60;
        String formattedTime = String.valueOf((convertedDays != 0) ? ((convertedDays == 1) ? (String.valueOf(convertedDays) + " dzień ") : (String.valueOf(convertedDays) + "dni ")) : "") + (
                (convertedHours != 0) ? ((convertedHours == 1) ? (String.valueOf(convertedHours) + " godzina ") : (String.valueOf(convertedHours) + " godziny ")) : "") + (
                (convertedMinutes != 0) ? ((convertedMinutes == 1) ? (String.valueOf(convertedMinutes) + " minuta ") : (String.valueOf(convertedMinutes) + " minuty ")) : "") + (
                (convertedSeconds != 0) ? ((convertedSeconds == 1) ? (String.valueOf(convertedSeconds) + " sekunda ") : (String.valueOf(convertedSeconds) + " sekundy ")) : "");
        return formattedTime;
    }

}
