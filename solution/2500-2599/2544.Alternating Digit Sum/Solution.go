func alternateDigitSum(n int) int {
	ans, sign := 0, 1
	for _, c := range strconv.Itoa(n) {
		x := int(c - '0')
		ans += sign * x
		sign *= -1
	}
	return ans
}