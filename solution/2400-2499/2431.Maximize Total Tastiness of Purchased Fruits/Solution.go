func maxTastiness(price []int, tastiness []int, maxAmount int, maxCoupons int) int {
	n := len(price)
	f := make([][][]int, n+1)
	for i := range f {
		f[i] = make([][]int, maxAmount+1)
		for j := range f[i] {
			f[i][j] = make([]int, maxCoupons+1)
		}
	}
	var dfs func(i, j, k int) int
	dfs = func(i, j, k int) int {
		if i == n {
			return 0
		}
		if f[i][j][k] != 0 {
			return f[i][j][k]
		}
		ans := dfs(i+1, j, k)
		if j >= price[i] {
			ans = max(ans, dfs(i+1, j-price[i], k)+tastiness[i])
		}
		if j >= price[i]/2 && k > 0 {
			ans = max(ans, dfs(i+1, j-price[i]/2, k-1)+tastiness[i])
		}
		f[i][j][k] = ans
		return ans
	}
	return dfs(0, maxAmount, maxCoupons)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}