func intToRoman(num int) string {
	cs := []string{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"}
	vs := []int{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1}
	ans := &strings.Builder{}
	for i, v := range vs {
		for num >= v {
			num -= v
			ans.WriteString(cs[i])
		}
	}
	return ans.String()
}