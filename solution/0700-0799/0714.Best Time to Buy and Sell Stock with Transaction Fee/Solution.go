func maxProfit(prices []int, fee int) int {
	f1, f2 := -prices[0], 0
	for i := 1; i < len(prices); i++ {
		f1 = max(f1, f2-prices[i])
		f2 = max(f2, f1+prices[i]-fee)
	}
	return f2
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}