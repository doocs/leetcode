func alternateDigitSum(n int) (ans int) {
	sign := 1
	for _, c := range strconv.Itoa(n) {
		x := int(c - '0')
		ans += sign * x
		sign *= -1
	}
	return
}