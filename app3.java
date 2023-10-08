import java.util.Scanner;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class app3 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите данные пользователя (Фамилия Имя Отчество дата_рождения(dd.mm.yyyy) номер_телефона пол(f,m)):");
            String input = scanner.nextLine();

            if (input.trim().isEmpty()) {
                throw new IllegalArgumentException("Нельзя вводить пустые строки");
            }

            String[] data = input.split(" ");
        
            if (data.length != 6) {
                System.out.println("Неверное количество данных");
            } else {
                String lastName = parseLastName(data[0]);
                String firstName = parseFirstName(data[1]);
                String middleName = parseMiddleName(data[2]);
                LocalDate birthDate = parseBirthDate(data[3]);
                long phoneNumber = parsePhoneNumber(data[4]);
                char gender = parseGender(data[5]);

                String userData = String.format("%s %s %s %s %d %c", lastName, firstName, middleName, birthDate,
                        phoneNumber, gender);

                try (FileWriter fileWriter = new FileWriter(lastName + ".txt", true)) {
                    fileWriter.write(userData + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());

        }
    }

    private static String parseLastName(String input) {
        if (!input.matches("[a-zA-Zа-яёА-ЯЁ]+")) {
            throw new IllegalArgumentException("Фамилия содержит недопустимые символы");
        }
        return input;
    }

    private static String parseFirstName(String input) {
        if (!input.matches("[a-zA-Zа-яёА-ЯЁ]+")) {
            throw new IllegalArgumentException("Имя содержит недопустимые символы");
        }
        return input;
    }

    private static String parseMiddleName(String input) {
        if (!input.matches("[a-zA-Zа-яёА-ЯЁ]+")) {
            throw new IllegalArgumentException("Отчество содержит недопустимые символы");
        }
        return input;
    }

    private static LocalDate parseBirthDate(String input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return LocalDate.parse(input, formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Неверный формат даты рождения");
        }
    }

    private static long parsePhoneNumber(String input) {
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Номер телефона должен быть целым числом");
        }
    }

    private static char parseGender(String input) {
        if (!input.matches("[fmFM]")) {
            throw new IllegalArgumentException("Пол должен быть 'f' или 'm'");
        }
        return Character.toLowerCase(input.charAt(0));
    }
}