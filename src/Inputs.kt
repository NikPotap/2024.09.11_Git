fun selectCity(city1: String, city2: String): Int {
    while (true) {
        var input: String
        print("\nВыберите ваш город\n1 - $city1\n2 - $city2\nдля завершения нажмите \"Enter\": ")
        input = readln()
        if (input == "") return 0
        if (input == "1") return 1
        if (input == "2") return 2
        println("Неверный ввод")
    }
}

fun selectAction(needRepair: Boolean): Int {
    while (true) {
        var input: String
        if (needRepair) {
            print("\nВыберите операцию\n1 - выбрать телефон\n2 - посмотреть статитику продаж\n3 - сдать телефон в ремонт\nдля смены города нажмите \"Enter\": ")
        } else {
            print("\nВыберите операцию\n1 - выбрать телефон\n2 - посмотреть статитику продаж\nдля смены города нажмите \"Enter\": ")
        }
        input = readln()
        if (input == "") return 0
        if (input == "1") return 1
        if (input == "2") return 2
        if ((input == "3") && needRepair) return 3
        println("Неверный ввод")
    }
}

fun selectPhone(list: List<Warehouse>): Int {
    while (true) {
        var input: String
        print("Выберите телефон (1-5): ")
        input = readln()
        if (input.toIntOrNull() == null) {
            println("Неверный ввод")
            continue
        }
        if (input.toInt() in 1..5) return input.toInt()
        println("Неверный ввод")
    }
}