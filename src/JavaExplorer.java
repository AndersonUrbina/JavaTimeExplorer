import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class JavaExplorer {
    //CONSTANTS FOR TIME FORMATTERS

    //Date formatter: 19/11/2026
    private static final DateTimeFormatter DATE_INPUT_FORMAT =
            DateTimeFormatter.ofPattern("MM/dd/yyyy");

    // Date formatter: 01:05
    private static final DateTimeFormatter TIME_INPUT_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm");

    // Date formatter:
    private static final DateTimeFormatter LONG_DATE_FORMAT =
            DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");

    private static final DateTimeFormatter FULL_DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy h:mm a");

    //Main Method
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean keepRunning = true;

        System.out.println();

        //While to handle user input
        while (keepRunning){
            printMenu();
            System.out.print("Enter your choice: ");
            String choice = input.nextLine();

            System.out.println("-----------------------");
            switch (choice){
                case "1":
                    showCurrentDateAndTime();
                    break;
                case "2":
                    showFormatedDate();
                    break;
                case "3":
                    parsedDateFromText(input);
                    break;
                case "4":
                    calculateDateDifference(input);
                    break;
                case "5":
                    countdownToFutureDate(input);
                    break;
                case "6":
                    calculateTimeDuration(input);
                    break;
                case "7":
                    convertTimeZone();
                    break;
                case "8":
                    displayInstantTimeStamp();
                    break;
                case "9":
                    keepRunning = false;
                    System.out.println("Good Bye!");
                    break;
                default:
                    System.out.println("Invalid menu option. Please choose a number from 1 through 9");
            }
            System.out.println("-----------------------");
            System.out.println();
        }


        input.close();
    }
    //Helper Method to print the menu
    public static void printMenu(){
        System.out.println("Java Time Explorer");
        System.out.println();
        System.out.println("1) Show current date and time");
        System.out.println("2) Format a Date");
        System.out.println("3) Parse a Date from text");
        System.out.println("4) Calculate age or event difference");
        System.out.println("5) Countdown to a future date");
        System.out.println("6) Calculate time duration");
        System.out.println("7) Convert Time zone");
        System.out.println("8) Show Current time Stamp");
        System.out.println("9) Exit");
        System.out.println();
    }


    //TIME HANDLING METHOD

    // 1 - Void to show the current date and time
    public static void showCurrentDateAndTime(){
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        LocalDateTime currentDateTime = LocalDateTime.now();

        System.out.println("Current Date and Time:");
        System.out.println();

        System.out.println("Current Date: " + currentDate);
        System.out.println("Current Time: " + currentTime.truncatedTo(ChronoUnit.SECONDS));
        System.out.println("Current Date & Time: " + currentDateTime.truncatedTo(ChronoUnit.SECONDS));
    }

    // 2 - Void to show formated date
    public static void showFormatedDate(){
        LocalDate currentDate = LocalDate.now();

        System.out.println("Date Formatting:");

        System.out.println("Default Format: " + currentDate);
        System.out.println("Short Format: " + currentDate.format(DATE_INPUT_FORMAT));
        System.out.println("Long Format: " + currentDate.format(LONG_DATE_FORMAT));
    }

    // 3 - Void to calculate the difference between two dates
    public static void parsedDateFromText(Scanner input){
        System.out.println("Date Parsing: ");
        System.out.print("Enter a date using MM/dd/yyyy format: ");
        String userDate = input.nextLine();

        try{
           LocalDate parsedDate = LocalDate.parse(userDate, DATE_INPUT_FORMAT);

           System.out.println();
           System.out.println("User entry: " + userDate);
           System.out.println("Parsed LocalDate: " + parsedDate);
           System.out.println("Formatted Date: " + parsedDate.format(LONG_DATE_FORMAT));

        } catch (DateTimeParseException e){
            System.out.println("Invalid Data format");
        }
    }

    // 4 - Void to calculate the difference between Two dates
    public static void calculateDateDifference(Scanner input){
        System.out.println("Age or Event Difference Calculator. ");
        System.out.print("Enter a past date using MM/dd/yyyy format: ");
        String userDate = input.nextLine();

        try{
            LocalDate pastDate = LocalDate.parse(userDate, DATE_INPUT_FORMAT);
            LocalDate currentDate = LocalDate.now();

            //Handle for future dates
            if (pastDate.isAfter(currentDate)){
                System.out.println("The date is in the future! Enter a past date.");
                return;
            }

            //Get the difference
            Period difference = Period.between(pastDate, currentDate);

            System.out.println("Time Since: " + pastDate.format(LONG_DATE_FORMAT));
            System.out.println(difference.getYears() + " years, " + difference.getMonths() + " months, " + difference.getDays() + " days.");

        } catch (DateTimeParseException e){
            System.out.println("Invalid Data format");
        }
    }

    // 5 - Void to count down to future date
    public static void countdownToFutureDate(Scanner input){
        System.out.println("countdown to Future Date: ");

        System.out.print("Enter a future date using MM/dd/yyyy format: ");
        String userDate = input.nextLine();

        try{
            LocalDate futureDate = LocalDate.parse(userDate, DATE_INPUT_FORMAT);
            LocalDate currentDate = LocalDate.now();

            if (futureDate.isBefore(currentDate)){
                System.out.println("The date is in the past! Enter a future date.");
                return;
            }

            long daysUntil = ChronoUnit.DAYS.between(currentDate, futureDate);
            System.out.println("Future Date: " + futureDate.format(LONG_DATE_FORMAT));
            System.out.println("Days until that date: " + daysUntil);

        } catch (DateTimeParseException e){
            System.out.println("Invalid Data format");
        }

    }

    // 6 - Void to calculate time duration
    public static void calculateTimeDuration(Scanner input){
        System.out.println("Time Duration Calculator");
        System.out.println();

        //Get two times from the user
        System.out.print("Enter start time using HH:mm format: ");
        String startText = input.nextLine();
        System.out.print("Enter end time using HH:mm format: ");
        String endText = input.nextLine();

        try{
            LocalTime startTime = LocalTime.parse(startText, TIME_INPUT_FORMAT);
            LocalTime endTime = LocalTime.parse(endText, TIME_INPUT_FORMAT);

            if(endTime.isBefore(startTime)){
                System.out.println("The time is before the start time!");
                return;
            }

            Duration  duration = Duration.between(startTime, endTime);

            long hours = duration.toHours();
            long minutes = duration.toMinutes();

            System.out.println("Raw Duration " + duration);
            System.out.println("Hours: " + hours);
            System.out.println("Minutes: " + minutes);
            System.out.println("Duration: " + hours + " hours, " + minutes + " minutes.");

        } catch (DateTimeParseException e){
            System.out.println("Invalid Data format");
        }

    }

    // 7 - Void to convert to multiple time zones
    public static void convertTimeZone(){
        System.out.println("Time Zone Converter");
        System.out.println();

        //Define Time Zone
        ZoneId chicagoZone = ZoneId.of("America/Chicago");
        ZoneId newYorkZone = ZoneId.of("America/New_York");
        ZoneId losAngelesZone = ZoneId.of("America/Los_Angeles");
        ZoneId londonZone = ZoneId.of("Europe/London");
        ZoneId tokyoZone = ZoneId.of("Asia/Tokyo");

        //Hardcode the date and time
        LocalDate meetingDate = LocalDate.of(2026, 12, 15);
        LocalTime meetingTime = LocalTime.parse("12:15");

        //convert to a zoned date time
        ZonedDateTime chicagoMeeting = ZonedDateTime.of(meetingDate, meetingTime, chicagoZone);

        //Convert the Chicago Time to other Time zones
        ZonedDateTime newYorkMeeting = chicagoMeeting.withZoneSameInstant(newYorkZone);
        ZonedDateTime losAngelesMeeting = chicagoMeeting.withZoneSameInstant(losAngelesZone);
        ZonedDateTime londonMeeting = chicagoMeeting.withZoneSameInstant(londonZone);
        ZonedDateTime tokyoMeeting = chicagoMeeting.withZoneSameInstant(tokyoZone);

        //Print all date times
        System.out.println("Meeting Time:");
        System.out.println("Chicago: " + chicagoMeeting.format(FULL_DATE_TIME_FORMAT));
        System.out.println("New York: " + newYorkMeeting.format(FULL_DATE_TIME_FORMAT));
        System.out.println("Los Angeles: " + losAngelesMeeting.format(FULL_DATE_TIME_FORMAT));
        System.out.println("London: " + londonMeeting.format(FULL_DATE_TIME_FORMAT));
        System.out.println("Tokyo: " + tokyoMeeting.format(FULL_DATE_TIME_FORMAT));
    }

    // 8 - Void to get the current instant
    public static void displayInstantTimeStamp(){
        System.out.println("Current Instant");
        System.out.println();

        Instant instant = Instant.now();
        System.out.println("The specific moment in computer time: " + instant);

    }
}

