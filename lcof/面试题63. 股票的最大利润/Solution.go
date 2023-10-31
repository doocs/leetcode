func maxProfit(prices []int) (ans int) {
	mi := 1 << 30
	for _, x := range prices {
		ans = max(ans, x-mi)
		mi = min(mi, x)
	}
	return
}