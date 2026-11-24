package org.example;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

public class Main {
    public static void main(String[] args) {
        Country[] countries = {
                new Country("Russia", 17100000, 146700000L, "Moscow", 12600000L),
                new Country("Finland", 338000, 5500000L, "Helsinki", 655000L),
                new Country("France", 643800, 67800000L, "Paris", 2100000L),
                new Country("Andorra", 467, 85400L, "Andorra la Vella", 22600L),
                new Country("Singapore", 2, 5700000L)
        };

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите команду (create, print, exit): ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "create" -> {
                    Country newCountry = crateNewCountryFromConsole(scanner);
                    countries = Arrays.copyOf(countries, countries.length + 1);
                    countries[countries.length - 1] = newCountry;
                    System.out.println("Страна добавлена: " + newCountry.getName() + ".");
                }
                case "print" -> printAll(countries);
                case "exit" -> {
                    System.out.println("Программа завершена.");
                    return;
                }
                default -> System.out.println("Неизвестная команда. Доступные: create, print, exit.");
            }


        }
    }

    public static void printAll(Country[] countries) {
        if (isEmpty(countries))
            return;

        for (Country country : countries) {
            if (!isEmpty(country))
                System.out.println("\n" + country);
        }
    }

    public static Country crateNewCountryFromConsole(Scanner scanner) {
        while (true) {
            try {
                String name = readText(scanner, "Введите название страны:");
                double area = readDouble(scanner, "Введите площадь (км²):");
                long population = readLong(scanner, "Введите население:");

                String hasCapital;
                while (true) {
                    System.out.println("Есть ли столица? (y/n):");
                    hasCapital = scanner.nextLine().trim().toLowerCase();
                    if (hasCapital.equals("y")) {
                        String capitalName = readText(scanner, "Введите название столицы:");
                        long capitalPopulation = readLong(scanner, "Введите население столицы:");
                        return new Country(name, area, population, capitalName, capitalPopulation);
                    } else if (hasCapital.equals("n")) {
                        break;
                    }
                    System.out.println("Ошибка. Введите 'y' или 'n'.");
                }
                return new Country(name, area, population);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка валидации: " + e.getMessage() + " Попробуйте ввести данные заново.\n");
            }
        }
    }

    public static double readDouble(Scanner scanner, String text) {
        while (true) {
            System.out.println(text);
            try {
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода. Введите корректное числовое значение(напрмиер: 1234,5)");
                scanner.nextLine();
            }
        }
    }

    public static long readLong(Scanner scanner, String text) {
        while (true) {
            System.out.println(text);
            try {
                long value = scanner.nextLong();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода. Введите корректное цнлое числовое значение.");
                scanner.nextLine();
            }
        }
    }

    public static String readText(Scanner scanner, String text) {
        while (true) {
            System.out.println(text);
            String value = scanner.nextLine().trim();

            if (value.isEmpty()) {
                System.out.println("Ошибка: название не может быть пустым.");
                continue;
            } else if (value.equals(".*\\d.*")) {
                System.out.println("Ошибка: название не должно содержать цифры.");
                continue;
            }

            return value;
        }
    }
}