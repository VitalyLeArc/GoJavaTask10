package classes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Shop {

    private String dataBasePath; // путь по умолчанию где хранится бд магаза
    private int shopIndex;
    private int shopMoney;
    private ArrayList<Fruit> storage=new ArrayList<>();
    private boolean storageOpened=false;  //переменная нужна чтобы случайно не обнулить инфу склада
    public Shop(int shopIndex,int shopMoney,String dataBasePath){
        this.shopIndex = shopIndex;
        this.shopMoney = shopMoney;
        this.dataBasePath =dataBasePath;
    }
    public int getShopMoney(){
        return shopMoney;
    }
    public int getShopIndex(){
        return shopIndex;
    }
    public String getDataBasePath(){
        return dataBasePath;
    }
    //задание 1
    public void addFruits(String pathToJsonFile){
        if(storageOpened){
            try {
                File file = new File(pathToJsonFile);
                Scanner scan = new Scanner(file);
                String json="";
                while(scan.hasNext()){
                    json+=scan.nextLine();                      //считываем весь файл в 1 строку (ЖСОН дальше сам разберётся)
                }

                ArrayList<Fruit> tmp = new ArrayList<>();
                TypeReference<ArrayList<Fruit>> typeRef= new TypeReference<>(){};
                tmp.clear();
                tmp = JSON.parseObject(json,typeRef);           //парсим файл во временный список

                for(Fruit fruit:tmp) {                          //проходимся по временному списку и добавляем в склад попутно вычитая деньги
                    storage.add(fruit);
                    shopMoney -= fruit.getCost();
                    System.out.println("Потрачено "+fruit.getCost());
                }
                scan.close();           //без закрытия скана не удалится файл
                file.delete();          //если до сюда ничего исключение не выкинуло значит можно удалять то что добавили

            }catch (Exception e){
                System.out.println("Не удалось добавить поставку");
            }
        }
        else {
            System.out.println("Сперва нужно загрузить текущую информацию о складе");
        }
    }
    public void save(String pathToJsonFile){
        try {
            File file = new File(pathToJsonFile);
            FileWriter fw = new FileWriter(file);
            fw.write(JSON.toJSONString(storage));
            fw.close();
            System.out.println("Данные о складе сохранены");
        }catch (Exception e){
            System.out.println("Не удалось сохранить информацию о складе");
        }
    }
    public void load(String pathToJsonFile){
            try {
                if (new File(pathToJsonFile).isFile()){ //Небольшая проверка на существование вообще бд
                    File file = new File(pathToJsonFile);
                    Scanner scan = new Scanner(file);
                    String json="";
                    while(scan.hasNext()) {
                        json += scan.nextLine();
                    }
                    TypeReference<ArrayList<Fruit>> typeRef = new TypeReference<>() {
                    };
                    storage.clear();
                    storage = JSON.parseObject(json, typeRef);
                    System.out.println("Данные о складе загружены");
                    scan.close();
                }
                storageOpened = true;
            } catch (Exception e) {
                System.out.println("Не удалось открыть информацию о складе");
            }
    }
    //задание 2
    public void printSpoiledFruits(String expectedDate){
        boolean existSpoiled = false;
        try {
            LocalDate date = parseDate(expectedDate);
        if(!storage.isEmpty()){
            for(Fruit fruit:storage){
                if(fruit.getInitialDate().plusDays(fruit.getShelfLife()).compareTo(date)<0){      //к исходной дате добавляется срок годности и сравнивается с искомой датой, если меньше 0 - знач не просрочилось
                    System.out.println(fruit.getType()+ " от "+fruit.getInitialDate().format(formatter)+" просрочится к "+ expectedDate);
                    existSpoiled = true;
                }
            }
        }
        if(existSpoiled){
            System.out.println("Испорченного ничего нет");
        }
        }catch (Exception e){
            System.out.println("Неверная дата");
        }
    }
    public void printAvaliableFruits(String expectedDate){
        boolean existReady = false;
        try {
            LocalDate date = parseDate(expectedDate);
            if(!storage.isEmpty()){
                for(Fruit fruit:storage){
                    if(fruit.getInitialDate().compareTo(date)<0){      //если больше 0 - знач ещё не доставлено на склад
                        System.out.println(fruit.getType()+ " от "+fruit.getInitialDate().format(formatter)+" будет готов ");
                        existReady = true;
                    }
                }
            }
            if(!existReady){
                System.out.println("Готового ничего нет");
            }
        }catch (Exception e){
            System.out.println("Неверная дата");
        }
    }
    public ArrayList<Fruit> getSpoiledFruits(String expectedDate){
        ArrayList<Fruit> spoiled = new ArrayList<>();
        try {
            LocalDate date = parseDate(expectedDate);
            if(!storage.isEmpty()){
                for(Fruit fruit:storage){
                    if(fruit.getInitialDate().plusDays(fruit.getShelfLife()).compareTo(date)<0){      //к исходной дате добавляется срок годности и сравнивается с искомой датой, если меньше 0 - знач не просрочилось
                        spoiled.add(fruit);
                    }
                }
            }
            if(spoiled.isEmpty()){
                System.out.println("Испорченного ничего нет");
            }
        }catch (Exception e){
            System.out.println("Неверная дата");
        }

        return spoiled;
    }
    public ArrayList<Fruit> getAvaliableFruits(String expectedDate){
        ArrayList<Fruit> avaliable = new ArrayList<>();
        try {
            LocalDate date = parseDate(expectedDate);
            if(!storage.isEmpty()){
                for(Fruit fruit:storage){
                    if(fruit.getInitialDate().compareTo(date)<0){      //если больше 0 - знач ещё не доставлено на склад
                       avaliable.add(fruit);
                    }
                }
            }
            if(avaliable.isEmpty()){
                System.out.println("Готового ничего нет");
            }
        }catch (Exception e){
            System.out.println("Неверная дата");
        }
        return avaliable;
    }
//задание 3
    public ArrayList<Fruit> getSpoiledFruits(Fruit.fruitsType type, String expectedDate){
        ArrayList<Fruit> spoiled = new ArrayList<>();
        try {
            LocalDate date = parseDate(expectedDate);
            if(!storage.isEmpty()){
                for(Fruit fruit:storage){
                    if(type==fruit.getType()&&fruit.getInitialDate().plusDays(fruit.getShelfLife()).compareTo(date)<0){      //к исходной дате добавляется срок годности и сравнивается с искомой датой, если меньше 0 - знач не просрочилось
                        spoiled.add(fruit);
                    }
                }
            }
            if(spoiled.isEmpty()){
                System.out.println("Из выбранного фрукта испорченного ничего нет");
            }
        }catch (Exception e){
            System.out.println("Неверная дата");
        }
        return spoiled;
    }
    public ArrayList<Fruit> getAvaliableFruits(Fruit.fruitsType type, String expectedDate){
        ArrayList<Fruit> avaliable = new ArrayList<>();
        try {
            LocalDate date = parseDate(expectedDate);
            if(!storage.isEmpty()){
                for(Fruit fruit:storage){
                    if(type==fruit.getType()&&fruit.getInitialDate().compareTo(date)<0){      //если больше 0 - знач ещё не доставлено на склад
                        avaliable.add(fruit);
                    }
                }
            }
            if(avaliable.isEmpty()){
                System.out.println("Готового ничего нет");
            }
        }catch (Exception e){
            System.out.println("Неверная дата");
        }
        return avaliable;
    }
    public ArrayList<Fruit> getAddedFruits(String expectedDate){
        ArrayList<Fruit> added = new ArrayList<>();
        try {
            LocalDate date = parseDate(expectedDate);
            if(!storage.isEmpty()){
                for(Fruit fruit:storage){
                    if(fruit.getInitialDate().compareTo(date)==0){
                        added.add(fruit);
                        System.out.print(fruit.getType()+" добавлен в список добавленного. ");
                    }
                }
                System.out.println();
            }
            if(added.isEmpty()){
                System.out.println("На указанную дату ничего не доставлялось");
            }
        }catch (Exception e){
            System.out.println("Неверная дата");
        }
        return added;
    }
    public ArrayList<Fruit> getAddedFruits(Fruit.fruitsType type,String expectedDate){
        ArrayList<Fruit> added = new ArrayList<>();
        try {
            LocalDate date = parseDate(expectedDate);
            if(!storage.isEmpty()){
                for(Fruit fruit:storage){
                    if(type==fruit.getType()&&fruit.getInitialDate().compareTo(date)==0){
                        added.add(fruit);
                        System.out.print(fruit.getType()+" добавлен в список добавленного. ");
                    }
                }
                System.out.println();
            }
            if(added.isEmpty()){
                System.out.println("Из выбранного фрукта на указанную дату ничего не доставлялось");
            }
        }catch (Exception e){
            System.out.println("Неверная дата");
        }
        return added;
    }


    public void addFruits(String fileName,boolean useStandartPath){
        if(useStandartPath) {
            addFruits(dataBasePath + "/" + fileName+".txt");
        }
    }
    public void save() {
        save(dataBasePath + "ShopStorage"+ shopIndex+".txt");
    }
    public void load(){
        load(dataBasePath+"ShopStorage"+shopIndex+".txt");
    }
    private LocalDate parseDate(String dateStr) {
        return LocalDate.parse(dateStr, formatter);
    }
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  //формат принимаемой даты в стринге
}
