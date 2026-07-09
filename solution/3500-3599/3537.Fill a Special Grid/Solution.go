func specialGrid(n int) [][]int {
	m := 1 << n
	ans := make([][]int, m)
	for i := range ans {
		ans[i] = make([]int, m)
	}
	val := 0

	var dfs func(int, int, int)
	dfs = func(x, y, k int) {
		if k == 1 {
			ans[x][y] = val
			val++
			return
		}

		h := k / 2
		dfs(x, y, h)
		dfs(x+h, y, h)
		dfs(x+h, y-h, h)
		dfs(x, y-h, h)
	}

	dfs(0, m-1, m)
	return ans
}
