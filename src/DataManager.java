import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class DataManager {
    // Имитация базы данных
    private Map<Integer, DataEntry> database = new HashMap<>();
    // Кэш для read-only данных
    private Map<Integer, DataEntry> cache = new HashMap<>();

    // Основной метод для получения данных
    public DataEntry getData(int id) {
        // Сначала проверяем кэш
        if (cache.containsKey(id)) {
            return cache.get(id);
        }

        // Если данных нет в кэше, запрашиваем из "базы данных"
        DataEntry entry = database.get(id);
        if (entry != null && entry.isReadOnly()) {
            // Если данные read-only, добавляем их в кэш
            cache.put(id, entry);
        }
        return entry;
    }

    // Метод для сохранения данных
    public void saveData(DataEntry data) {
        database.put(data.getId(), data);
        // Если данные не read-only, удаляем их из кэша
        if (!data.isReadOnly()) {
            cache.remove(data.getId());
        }
    }

    // Метод для обновления данных
    public void updateData(DataEntry data) {
        database.put(data.getId(), data);
        // Удаляем из кэша, если данные изменяемые
        cache.remove(data.getId());
    }

    // Метод для формирования отчета
    public List<DataEntry> generateReport() {
        List<DataEntry> report = new ArrayList<>();
        for (DataEntry entry : database.values()) {
            if (entry.isReadOnly()) report.add(cache.get(entry.getId()));
            else {
                report.add(entry);
            }
        }
        return report;
    }

    // Метод для выгрузки данных
    public void exportData(int id) {
        DataEntry data = getData(id);
        if (data != null) {
            System.out.println("Выгрузка данных: " + data);
        }
    }

    // Класс для представления данных
    public static class DataEntry {
        private int id;
        private String value;
        private boolean readOnly;

        public DataEntry(int id, String value, boolean readOnly) {
            this.id = id;
            this.value = value;
            this.readOnly = readOnly;
        }

        public int getId() {
            return id;
        }

        public String getValue() {
            return value;
        }

        public boolean isReadOnly() {
            return readOnly;
        }


    }
    }