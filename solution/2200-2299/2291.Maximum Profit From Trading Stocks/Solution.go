func maximumProfit(present []int, future []int, budget int) int {
	f := make([]int, budget+1)
	for i, a := range present {
		for j := budget; j >= a; j-- {
			f[j] = max(f[j], f[j-a]+future[i]-a)
		}
	}
	return f[budget]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}