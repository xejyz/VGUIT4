import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DataManager manager = new DataManager();

        // Создаем тестовые данные
        manager.saveData(new DataManager.DataEntry(1, "Данные 1", true));  // read-only
        manager.saveData(new DataManager.DataEntry(2, "Данные 2", false)); // изменяемые
        manager.saveData(new DataManager.DataEntry(3, "Данные 3", true));  // read-only

        // Получаем данные
        System.out.println("Получение данных из кэша: " + manager.getData(1));
        System.out.println("Получение изменяемых данных: " + manager.getData(2));
        System.out.println("Получение данных из кэша: " + manager.getData(3));

        // Формируем отчет
        System.out.println("\nОтчет:");
        for (DataManager.DataEntry entry : manager.generateReport()) {
            System.out.println(entry);
        }

        // Выгрузка данных
        manager.exportData(1);
        manager.exportData(2);
        manager.exportData(3);
    }
}


