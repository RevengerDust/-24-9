//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    println("=== Калькулятор скидок для меломанов ===")


    val user1 = User(purchasesAmount = 500.0, isRegular = false)
    val user2 = User(purchasesAmount = 5000.0, isRegular = false)
    val user3 = User(purchasesAmount = 50000.0, isRegular = false)
    val user4 = User(purchasesAmount = 500.0, isRegular = true)
    val user5 = User(purchasesAmount = 5000.0, isRegular = true)
    val user6 = User(purchasesAmount = 50000.0, isRegular = true)

    val testAmount = 1000.0 // Сумма текущей покупки

    println("Текущая покупка: ${formatMoney(testAmount)}")
    println()

    println("=== Примеры расчетов ===")
    calculateAndPrint(user1, testAmount, "Новичок (500 руб, не постоянный)")
    calculateAndPrint(user2, testAmount, "Средний (5000 руб, не постоянный)")
    calculateAndPrint(user3, testAmount, "VIP (50000 руб, не постоянный)")
    calculateAndPrint(user4, testAmount, "Новичок-меломан (500 руб, постоянный)")
    calculateAndPrint(user5, testAmount, "Средний-меломан (5000 руб, постоянный)")
    calculateAndPrint(user6, testAmount, "VIP-меломан (50000 руб, постоянный)")
}
data class User(
    val purchasesAmount: Double,
    val isRegular: Boolean
)
fun calculateDiscount(purchasesAmount: Double): Double {
    return when {
        purchasesAmount <= 1000.0 -> 0.0
        purchasesAmount <= 10000.0 -> 100.0
        else -> purchasesAmount * 0.05
    }
}
fun calculateTotal(user: User, currentPurchase: Double): Double {
    val discount = calculateDiscount(user.purchasesAmount)
    var total = if (discount > 0) {
        val discountedAmount = currentPurchase - discount
        if (discountedAmount < 0) 0.0 else discountedAmount
    } else {
        currentPurchase
    }
    if (user.isRegular) {
        total *= 0.99
    }
    return total
}
fun formatMoney(amount: Double): String {
    return String.format("%.2f руб.", amount)
}
fun calculateAndPrint(user: User, purchaseAmount: Double, description: String) {
    println("\n--- $description ---")
    println("Сумма предыдущих покупок: ${formatMoney(user.purchasesAmount)}")
    println("Постоянный покупатель: ${if (user.isRegular) "Да" else "Нет"}")
    println("Сумма текущей покупки: ${formatMoney(purchaseAmount)}")

    val discount = calculateDiscount(user.purchasesAmount)
    println("Базовая скидка: ${formatMoney(discount)}")

    val total = calculateTotal(user, purchaseAmount)
    println("Итоговая сумма: ${formatMoney(total)}")

    val saved = purchaseAmount - total
    println("Экономия: ${formatMoney(saved)}")
}
fun testBoundaryValues() {
    println("\n=== Тестирование граничных значений ===")

    val testCases = listOf(
        Pair(0.0, "0 руб."),
        Pair(1000.0, "1000 руб."),
        Pair(1001.0, "1001 руб."),
        Pair(5000.0, "5000 руб."),
        Pair(10000.0, "10000 руб."),
        Pair(10001.0, "10001 руб."),
        Pair(20000.0, "20000 руб.")
    )


    for ((amount, description) in testCases) {
        val discount = calculateDiscount(amount)
        println("Предыдущие покупки: $description -> скидка: ${formatMoney(discount)}")
    }
}
