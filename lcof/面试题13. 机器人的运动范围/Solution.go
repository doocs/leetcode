func movingCount(m int, n int, k int) int {
	var visited [][]bool
	visited = make([][]bool, m)
	for i := 0; i < m; i++ {
		visited[i] = make([]bool, n)
	}
	return dfs(0, 0, m, n, k, visited)
}

func dfs(x, y, m, n, k int, visited [][]bool) int {
	if x >= m || y >= n || visited[x][y] || (x%10+x/10+y%10+y/10) > k {
		return 0
	}
	visited[x][y] = true
	return 1 + dfs(x+1, y, m, n, k, visited) + dfs(x, y+1, m, n, k, visited)
}