func isPossibleToCutPath(grid [][]int) bool {
	m, n := len(grid), len(grid[0])
	dfs := func() bool {
		stk := [][2]int{{0, 0}}
		for len(stk) > 0 {
			i, j := stk[len(stk)-1][0], stk[len(stk)-1][1]
			stk = stk[:len(stk)-1]
			if i >= m || j >= n || grid[i][j] == 0 {
				continue
			}
			grid[i][j] = 0
			if i == m-1 && j == n-1 {
				return true
			}
			stk = append(stk, [2]int{i, j + 1}, [2]int{i + 1, j})
		}
		return false
	}
	a := dfs()
	grid[0][0], grid[m-1][n-1] = 1, 1
	b := dfs()
	return !(a && b)
}
