func countNumbersWithUniqueDigits(n int) int {
	if n == 0 {
		return 1
	}
	if n == 1 {
		return 10
	}
	ans := 10
	for i, cur := 0, 9; i < n-1; i++ {
		cur *= (9 - i)
		ans += cur
	}
	return ans
}