package classes;

public class SupplyCreator {
    public static void main(String[] args) {
        //код для генерации файлов, можно использовать как шаблон
        FruitJsonGenerator.newFruitJson(
                "Apple+Banan", Fruit.fruitsType.Apple,50,15,
                    Fruit.fruitsType.Banana,10,28,"12/11/2019");
        FruitJsonGenerator.newFruitJson(
                "Aiva+Pear", Fruit.fruitsType.Quince,90,80,
                    Fruit.fruitsType.Pear,92,55,"28/02/2020");
        FruitJsonGenerator.newFruitJson(
                "Plum+Orange", Fruit.fruitsType.Plum,120,60,
                Fruit.fruitsType.Orange,60,44,"20/02/2020");
        FruitJsonGenerator.newFruitJson(
                "Mango+Papaya", Fruit.fruitsType.Mango,45,71,
                Fruit.fruitsType.Papaya,55,67,"10/03/2020");
        FruitJsonGenerator.newFruitJson(
                "Cherry+Coconut", Fruit.fruitsType.Cherry,30,50,
                Fruit.fruitsType.Coconut,50,26,"20/06/2020");





        //StallSaveLoad.newStall(1,200,"src/main/db/");     //просто для инициализации базового файла
}}
