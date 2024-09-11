fun main() {
    println("Добро пожаловать в сеть магазинов «Молния»!")
    var listPhones: List<Warehouse>
    var shop: Shop
    val listPhonesMos = makeAssortMos()
    val listPhonesSam = makeAssortSam()
    var needRepair = true
    begin@ while (true) {
        val selectCity = selectCity("Москва", "Куйбышев")
        if (selectCity == 0) {
            println("До свидания!")
            return
        }
        if (selectCity == 1) {
            listPhones = listPhonesMos
            shop = MoscowShop()
        } else {
            listPhones = listPhonesSam
            needRepair = false
            shop = SamaraShop()
        }
        while (true) {
            val action = selectAction(needRepair)
            if (action == 0) continue@begin
            when (action) {
                1 -> {
                    shop.listing(listPhones)
                    val selPhone = selectPhone(listPhones)
                    shop.sale(selPhone, listPhones)
                    continue
                }

                2 -> {
                    shop.stat(listPhones)
                    continue
                }

                3 -> {
                    needRepair = false
                    shop.repair()
                    continue
                }
            }
        }
    }
}

interface Shop {
    fun sale(number: Int, list: List<Warehouse>)
    fun stat(list: List<Warehouse>) {
        var sum = 0
        println("\nПродано телефонов:")
        list.forEach { println("${it.name} - ${it.sold}."); sum += it.price.toInt() * it.sold }
        println("Всего на общую сумму $sum ₽.")
    }

    fun listing(list: List<Warehouse>) {
        println("\nСписок телефонов:")
        var num = 0
        list.forEach { println("${++num} - ${it.name}, стоимость ${it.price} ₽, остаток на складе ${it.quantity} шт.") }
    }

    fun repair() {}
}

abstract class Phone(val name: String, val price: String)

class Warehouse(name: String, price: String, var quantity: Int, var sold: Int) : Phone(name, price)

class MoscowShop : Shop {
    override fun sale(number: Int, list: List<Warehouse>) {
        val ind = number - 1
        if (list[ind].quantity == 0) {
            println("Этой модели в Москве не осталось.")
            return
        }
        println("Продан телефон ${list[ind].name}.")
        list[ind].quantity -= 1
        list[ind].sold += 1
    }

    override fun repair() {
        println("Ваше устройство отремонтировано.")
    }
}

class SamaraShop : Shop {
    override fun sale(number: Int, list: List<Warehouse>) {
        val ind = number - 1
        if (list[ind].quantity == 0) {
            println("Этой модели в Куйбышеве не осталось.")
            return
        }
        println("Продан телефон ${list[ind].name}.")
        list[ind].quantity -= 1
        list[ind].sold += 1
    }
}

