package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        // Створюємо дві змінні-"скарбнички" для підрахунку суми
        int totalSupply = 0;
        int totalBuy = 0;

        // 1. Читаємо вхідний CSV файл
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Розрізаємо рядок по комі на два елементи: [0] - тип, [1] - кількість
                String[] row = line.split(",");

                // Перевіряємо тип операції через .equals()
                if (row[0].equals("supply")) {
                    // Перетворюємо текст на число і плюсуємо в скарбничку supply
                    totalSupply += Integer.parseInt(row[1]);
                } else if (row[0].equals("buy")) {
                    // Перетворюємо текст на число і плюсуємо в скарбничку buy
                    totalBuy += Integer.parseInt(row[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        // 2. Рахуємо фінальну різницю за твоїм планом
        int result = totalSupply - totalBuy;

        // 3. Записуємо готовий звіт у новий файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + totalSupply + System.lineSeparator());
            writer.write("buy," + totalBuy + System.lineSeparator());
            writer.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
