func sellingWood(m int, n int, prices [][]int) int64 {
	memo := make([][]int, m+1)
	d := make([][]int, m+1)
	for i := range memo {
		memo[i] = make([]int, n+1)
		d[i] = make([]int, n+1)
		for j := range memo[i] {
			memo[i][j] = -1
		}
	}
	for _, p := range prices {
		d[p[0]][p[1]] = p[2]
	}
	var dfs func(int, int) int
	dfs = func(m, n int) int {
		if memo[m][n] != -1 {
			return memo[m][n]
		}
		ans := d[m][n]
		for i := 1; i < m/2+1; i++ {
			ans = max(ans, dfs(i, n)+dfs(m-i, n))
		}
		for i := 1; i < n/2+1; i++ {
			ans = max(ans, dfs(m, i)+dfs(m, n-i))
		}
		memo[m][n] = ans
		return ans
	}
	return int64(dfs(m, n))
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}