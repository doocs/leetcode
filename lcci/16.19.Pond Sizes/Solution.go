func pondSizes(land [][]int) (ans []int) {
	m, n := len(land), len(land[0])
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		res := 1
		land[i][j] = 1
		for x := i - 1; x <= i+1; x++ {
			for y := j - 1; y <= j+1; y++ {
				if x >= 0 && x < m && y >= 0 && y < n && land[x][y] == 0 {
					res += dfs(x, y)
				}
			}
		}
		return res
	}
	for i := range land {
		for j := range land[i] {
			if land[i][j] == 0 {
				ans = append(ans, dfs(i, j))
			}
		}
	}
	sort.Ints(ans)
	return
}