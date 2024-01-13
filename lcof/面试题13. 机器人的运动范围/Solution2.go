func movingCount(m int, n int, k int) (ans int) {
	f := func(x int) (s int) {
		for ; x > 0; x /= 10 {
			s += x % 10
		}
		return
	}
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}

	dirs := [3]int{1, 0, 1}
	var dfs func(i, j int)
	dfs = func(i, j int) {
		vis[i][j] = true
		ans++
		for l := 0; l < 2; l++ {
			x, y := i+dirs[l], j+dirs[l+1]
			if x >= 0 && x < m && y >= 0 && y < n && f(x)+f(y) <= k && !vis[x][y] {
				dfs(x, y)
			}
		}
	}
	dfs(0, 0)
	return
}