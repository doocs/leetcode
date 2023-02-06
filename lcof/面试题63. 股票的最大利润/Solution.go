func maxProfit(prices []int) (ans int) {
	mi := 1 << 30
	for _, x := range prices {
		ans = max(ans, x-mi)
		mi = min(mi, x)
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}