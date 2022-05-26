func minCount(coins []int) int {
	ans := 0
	for _, coin := range coins {
		ans += (coin + 1) / 2
	}
	return ans
}