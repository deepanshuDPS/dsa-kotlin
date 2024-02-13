package recursion


fun fabonnaci(n: Int): Int {
    if (n < 0) {
        throw IllegalArgumentException("n must be positive")
    }
    if (n == 0 || n == 1) {
        return n
    }
    return fabonnaci(n - 1) + fabonnaci(n - 2)
}

fun sumOfDigits(n: Int): Int {
    if (n < 0) {
        throw IllegalArgumentException("n must be positive")
    }
    if (n == 0) {
        return 0
    }
    return n % 10 + sumOfDigits(n / 10)
}

fun powOfNum(num: Double, pow: Int): Double {
    if (num < 0) {
        throw IllegalArgumentException("num must be integer")
    }
    if (pow == 0) return 1.0
    if (pow < 0)
        return 1 / num * powOfNum(num, pow + 1)
    return num * powOfNum(num, pow - 1)
}

//Euclidean Algorithm f(quotient,remainder)
fun gcd(num1: Int, num2: Int): Int {
    if (num2 == 0) return num1
    return gcd(num2, num1 % num2)
}

fun decToBin(num: Int): Int {
    if (num < 0) {
        throw IllegalArgumentException("num must be integer")
    }
    if (num == 0) return 0
    return decToBin(num / 2) * 10 + num % 2
}

fun main() {
    println(fabonnaci(6))
    println(sumOfDigits(1234))
    println(powOfNum(4.0, -1))
    println(gcd(18, 48))
    println(decToBin(13))
}