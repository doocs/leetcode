class Solution {
    fun addStrings(num1: String, num2: String): String {
        val result = mutableListOf<Int>()

        val chars_1 = num1.toCharArray()
        val chars_2 = num2.toCharArray()
        var over = 0
        var i = num1.length
        var j = num2.length

        while (i > 0 || j > 0 || over > 0) {
            val a = if (i > 0) chars_1[--i] - '0' else 0
            val b = if (j > 0) chars_2[--j] - '0' else 0
            val sum = a + b + over
            over = sum / 10
            result.add(sum % 10)
        }

        return result.reversed().joinToString("")
    }
}
