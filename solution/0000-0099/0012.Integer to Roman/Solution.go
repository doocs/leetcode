func intToRoman(num int) string {
	ans := ""
	values := []int{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1}
	romans := []string{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"}
	for i, value := range values {
		for value <= num {
			ans, num = ans+romans[i], num-value
		}
	}
	return ans
}