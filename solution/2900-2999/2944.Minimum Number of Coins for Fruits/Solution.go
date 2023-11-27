func minimumCoins(prices []int) int {
	n := len(prices)
	f := make([]int, n)
	var dfs func(int) int
	dfs = func(i int) int {
		if i > n {
			return 0
		}
		if f[i] == 0 {
			f[i] = 1 << 30
			for j := i + 1; j <= i*2+1; j++ {
				f[i] = min(f[i], dfs(j)+prices[j-1])
			}
		}
		return f[i]
	}
	return dfs(1)
}