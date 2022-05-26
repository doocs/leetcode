func maxProfit(prices []int) int {
	res, mi := 0, prices[0]
	for i := 1; i < len(prices); i++ {
		res = max(res, prices[i]-mi)
		mi = min(min, prices[i])
	}
	return res
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