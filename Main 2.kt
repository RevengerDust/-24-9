//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val testLikes = listOf(3, 5, 7, 13, 21, 32, 71, 101, 121, 1000, 1001, 1111)

    println("=== Генератор сообщений о лайках ===")
    println("Формат: Понравилось N людям/человеку/человекам")
    println()

    for (likes in testLikes) {
        val message = generateLikeMessage(likes)
        println("Лайков: $likes -> $message")
    }

    val input = readlnOrNull()

    if (input != null && input.matches(Regex("\\d+"))) {
        val likes = input.toInt()
        if (likes in 0..9999) {
            val message = generateLikeMessage(likes)
            println("\nРезультат: $message")

            showRuleForNumber(likes)
        } else {
            println("Ошибка: число должно быть от 0 до 9999")
        }
    } else {
        println("Ошибка: введите целое число")
    }
}


fun generateLikeMessage(likes: Int): String {
    return "Понравилось $likes ${getCorrectWord(likes)}"
}


fun getCorrectWord(n: Int): String {
    val lastTwoDigits = n % 100
    val lastDigit = n % 10

    return when {
        lastTwoDigits in 11..19 -> "людям"
        lastDigit == 1 -> "человеку"
        lastDigit in 2..4 -> "человекам"
        else -> "людям"
    }
}

fun showRuleForNumber(n: Int) {
    val lastTwoDigits = n % 100
    val lastDigit = n % 10

    println("\nПравило для числа $n:")
    println("Последние две цифры: $lastTwoDigits")
    println("Последняя цифра: $lastDigit")

    when {
        lastTwoDigits in 11..19 -> println("Исключение: числа 11-19 всегда используют 'людям'")
        lastDigit == 1 -> println("Оканчивается на 1 (кроме 11-19) → 'человеку'")
        lastDigit in 2..4 -> println("Оканчивается на 2, 3, 4 (кроме 12-14) → 'человекам'")
        else -> println("Оканчивается на 0, 5-9 или 11-19 → 'людям'")
    }
}

fun runComprehensiveTest() {
    println("\n=== Подробные тесты ===")

    val testCases = listOf(
        // Базовые случаи
        0 to "людям (ноль)",
        1 to "человеку",
        2 to "человекам",
        3 to "человекам",
        4 to "человекам",
        5 to "людям",

        11 to "людям (исключение 11-19)",
        12 to "людям (исключение 11-19)",
        15 to "людям (исключение 11-19)",
        19 to "людям (исключение 11-19)",

        20 to "людям",
        21 to "человеку",
        22 to "человекам",
        25 to "людям",

        100 to "людям",
        101 to "человеку",
        102 to "человекам",
        111 to "людям (исключение 11-19)",
        121 to "человеку",
        124 to "человекам",
        125 to "людям",

        1000 to "людям",
        1001 to "человеку",
        1011 to "людям (исключение 11-19)",
        1021 to "человеку",
        1022 to "человекам",
        1025 to "людям"
    )

    for ((likes, expected) in testCases) {
        val result = getCorrectWord(likes)
        val correct = result == expected.split(" ")[0]
        val status = if (correct) "✓" else "✗"
        println("$status $likes → $result (ожидалось: $expected)")
    }
}
