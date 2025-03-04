func maxCoins(lane1 []int, lane2 []int) int64 {
	n := len(lane1)
	f := make([][2][3]int64, n)
	for i := range f {
		for j := range f[i] {
			for k := range f[i][j] {
				f[i][j][k] = -1
			}
		}
	}
	var dfs func(int, int, int) int64
	dfs = func(i, j, k int) int64 {
		if i >= n {
			return 0
		}
		if f[i][j][k] != -1 {
			return f[i][j][k]
		}
		x := int64(lane1[i])
		if j == 1 {
			x = int64(lane2[i])
		}
		ans := max(x, dfs(i+1, j, k)+x)
		if k > 0 {
			ans = max(ans, dfs(i+1, j^1, k-1)+x)
			ans = max(ans, dfs(i, j^1, k-1))
		}
		f[i][j][k] = ans
		return ans
	}
	ans := int64(-1e18)
	for i := range lane1 {
		ans = max(ans, dfs(i, 0, 2))
	}
	return ans
}
