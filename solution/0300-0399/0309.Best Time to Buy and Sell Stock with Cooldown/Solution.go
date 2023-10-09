func maxProfit(prices []int) int {
	f, f0, f1 := 0, 0, -prices[0]
	for _, x := range prices[1:] {
		f, f0, f1 = f0, max(f0, f1+x), max(f1, f-x)
	}
	return f0
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}