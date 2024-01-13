func spiralOrder(matrix [][]int) []int {
	if len(matrix) == 0 || len(matrix[0]) == 0 {
		return []int{}
	}
	m, n := len(matrix), len(matrix[0])
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}
	i, j, k := 0, 0, 0
	dirs := [5]int{0, 1, 0, -1, 0}
	ans := make([]int, m*n)
	for h := 0; h < m*n; h++ {
		ans[h] = matrix[i][j]
		vis[i][j] = true
		x, y := i+dirs[k], j+dirs[k+1]
		if x < 0 || y < 0 || x >= m || y >= n || vis[x][y] {
			k = (k + 1) % 4
			x, y = i+dirs[k], j+dirs[k+1]
		}
		i, j = x, y
	}
	return ans
}