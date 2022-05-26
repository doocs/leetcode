func maxProfit(prices []int) int {
	f1, f2, f3 := -prices[0], 0, 0
	for i := 1; i < len(prices); i++ {
		pf1, pf2, pf3 := f1, f2, f3
		f1 = max(pf1, pf3-prices[i])
		f2 = max(pf2, pf1+prices[i])
		f3 = max(pf3, pf2)
	}
	return f2
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}