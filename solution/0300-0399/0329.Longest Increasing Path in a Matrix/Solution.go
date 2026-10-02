func longestIncreasingPath(matrix [][]int) (ans int) {
	m, n := len(matrix), len(matrix[0])
	outdegree := make([][]int, m)
	length := make([][]int, m)
	for i := range outdegree {
		outdegree[i] = make([]int, n)
		length[i] = make([]int, n)
	}
	dirs := [5]int{-1, 0, 1, 0, -1}
	queue := make([][2]int, 0)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			length[i][j] = 1
			for k := 0; k < 4; k++ {
				x, y := i+dirs[k], j+dirs[k+1]
				if 0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j] {
					outdegree[i][j]++
				}
			}
			if outdegree[i][j] == 0 {
				queue = append(queue, [2]int{i, j})
			}
		}
	}
	for head := 0; head < len(queue); head++ {
		i, j := queue[head][0], queue[head][1]
		ans = max(ans, length[i][j])
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if 0 <= x && x < m && 0 <= y && y < n && matrix[x][y] < matrix[i][j] {
				length[x][y] = max(length[x][y], length[i][j]+1)
				outdegree[x][y]--
				if outdegree[x][y] == 0 {
					queue = append(queue, [2]int{x, y})
				}
			}
		}
	}
	return
}
