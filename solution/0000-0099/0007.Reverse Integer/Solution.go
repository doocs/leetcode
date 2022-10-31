func reverse(x int) int {
	ans, INT32_MAX, INT32_MIN := 0, math.MaxInt32, math.MinInt32
	for ; x != 0; x /= 10 {
		if ans > INT32_MAX/10 || ans < INT32_MIN/10 {
			return 0
		}
		ans = ans*10 + x%10
	}
	return ans
}