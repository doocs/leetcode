func maxProfit(prices []int, fee int) int {
	n := len(prices)
	f := make([][2]int, n+1)
	for i := n - 1; i >= 0; i-- {
		for j := 0; j < 2; j++ {
			ans := f[i+1][j]
			if j > 0 {
				ans = max(ans, prices[i]+f[i+1][0]-fee)
			} else {
				ans = max(ans, -prices[i]+f[i+1][1])
			}
			f[i][j] = ans
		}
	}
	return f[0][0]
}
