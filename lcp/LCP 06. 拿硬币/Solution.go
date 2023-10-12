func minCount(coins []int) (ans int) {
	for _, x := range coins {
		ans += (x + 1) >> 1
	}
	return
}