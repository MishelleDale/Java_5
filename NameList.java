import java.util.*;
import java.util.logging.Logger;

public class NameList {

    public static void main(String[] args) {
    
	    List<String> people = Arrays.stream(new String[]{"Иван Иванов", "Светлана Петрова", "Кристина Белова", "Анна Мусина", "Анна Крутова", "Иван Юрин", "Иван Юрин", "Петр Лыков", "Павел Чернов", "Петр Чернышов", "Мария Федорова", "Марина Светлова", "Мария Савина", "Мария Рыкова", "Марина Лугова", "Анна Владимирова", "Иван Мечников", "Петр Петин", "Иван Ежов"}).toList();

        Workers workers = new Workers(people);

        workers.printListWorkers();
        System.out.println();

        workers.printUnicKeyForListWorkers();
        System.out.println();

        workers.printCountUnicKeyWorkers();
        System.out.println();

        workers.printSortedListWorkers();

    }
}


class Workers {
    protected HashMap<String, String> mapWorkers = new HashMap<>();
    protected HashSet<String> setUnicKey = new HashSet<>();
    protected List<String> listWorkers = new ArrayList<>();
    private Logger logger = Logger.getAnonymousLogger();

    Workers(List<String> workersList) {
        logger.info("Инициализации списка сотрудников");
        listWorkers = workersList;
        logger.info("Инициализации уникальных ключей карты сотрудников");
        setUnicKey = getUnicKeyForListWorkers();
        logger.info("Карта сотрудников инициализирована");
    }

    public void printListWorkers() {
        logger.info("Запрос на вывод списка сотрудников в консоль");
        for (String person : listWorkers) {
            System.out.println(person);
        }
        logger.info("Список сотрудников успешно выведен в консоль");
    }

    private HashSet<String> getUnicKeyForListWorkers() {
        for (String set : listWorkers) {
            String[] setArray = set.split(" ");
            setUnicKey.add(setArray[0]);
        }
        return setUnicKey;
    } // to separate the first nane

    public void printUnicKeyForListWorkers() {
        logger.info("Запрос на вывод уникальных ключей из списка сотрудников");
        System.out.println(setUnicKey);
        logger.info("Уникальные ключи из списка сотрудников успешно выведены в консоль");
    }

   
    public HashMap<String, String> getCountUnicKeyListWorkers() {
        logger.info("Запрос на подсчет количества повторений уникальных ключей из списка сотрудников");
        HashMap<String, String> mapCountValueForUnicKey = new HashMap<>();

        for (String set : listWorkers) {
            String[] setArray = set.split(" ");
            if (! mapCountValueForUnicKey.containsKey(setArray[0])) {
                mapCountValueForUnicKey.put(setArray[0], "1");
            } else {
                String temp = Integer.toString(Integer.parseInt(mapCountValueForUnicKey.get(setArray[0])) + 1);
                mapCountValueForUnicKey.replace(setArray[0], temp);
            }
        }

        logger.info("Подсчет количества повторений уникальных ключей из списка сотрудников выполнен успешно");
        return mapCountValueForUnicKey;
    }

    public void printCountUnicKeyWorkers() {
        for (Map.Entry<String, String> map : getCountUnicKeyListWorkers().entrySet()) {
            System.out.println(map.getKey() + " " + map.getValue());
        }
    }

    public List<String> getSortedListWorkers() {
        logger.info("Запрос на получение отсортированного списка сотрудников");
        List<String> sortedListWorkers = new ArrayList<>();
        HashMap<String, String> countUnicKeyMapWorkers = getCountUnicKeyListWorkers();
        int maxValue = 0;

        for (Map.Entry<String, String> set : countUnicKeyMapWorkers.entrySet()) {
            if (Integer.parseInt(set.getValue()) > maxValue) {
                maxValue = Integer.parseInt(set.getValue());
            }
        }

        while (maxValue != 0) {
            for (Map.Entry<String, String> set : countUnicKeyMapWorkers.entrySet()) {
                if (Integer.parseInt(set.getValue()) == maxValue) {
                    for (String person : listWorkers) {
                        String[] personArray = person.split(" ");
                        if (personArray[0].equals(set.getKey())) {
                            sortedListWorkers.add(personArray[0] + " " + personArray[1]);
                        }
                    }
                }
            }
            maxValue--;
        }
        logger.info("Запрос на получение отсортированного списка сотрудников выполнен успешно");
        return sortedListWorkers;
    }

    public void printSortedListWorkers() {
        logger.info("Запрос на вывод в консоль отсортированного списка");
        for (String set : getSortedListWorkers()) {
            System.out.println(set);
        }
        logger.info("Вывод в консоль отсортированного списка выполнен успешно");
    }
}
