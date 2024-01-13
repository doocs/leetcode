func maxProfit(prices []int) int {
	n := len(prices)
	f := make([][2]int, n)
	f[0][1] = -prices[0]
	for i := 1; i < n; i++ {
		f[i][0] = max(f[i-1][0], f[i-1][1]+prices[i])
		if i > 1 {
			f[i][1] = max(f[i-1][1], f[i-2][0]-prices[i])
		} else {
			f[i][1] = max(f[i-1][1], -prices[i])
		}
	}
	return f[n-1][0]
}