func maximumProfit(present []int, future []int, budget int) int {
	arr := [][]int{}
	for i, v := range present {
		if future[i] > v {
			arr = append(arr, []int{v, future[i] - v})
		}
	}
	dp := make([]int, budget+1)
	for _, e := range arr {
		v, w := e[0], e[1]
		for j := budget; j >= v; j-- {
			dp[j] = max(dp[j], dp[j-v]+w)
		}
	}
	return dp[budget]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}