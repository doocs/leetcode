func pacificAtlantic(heights [][]int) [][]int {
	m, n := len(heights), len(heights[0])
	vis1 := make([][]bool, m)
	vis2 := make([][]bool, m)
	for i := range vis1 {
		vis1[i] = make([]bool, n)
		vis2[i] = make([]bool, n)
	}
	q1, q2 := [][2]int{}, [][2]int{}
	dirs := [5]int{-1, 0, 1, 0, -1}

	for i := 0; i < m; i++ {
		q1 = append(q1, [2]int{i, 0})
		vis1[i][0] = true
		q2 = append(q2, [2]int{i, n - 1})
		vis2[i][n-1] = true
	}
	for j := 0; j < n; j++ {
		q1 = append(q1, [2]int{0, j})
		vis1[0][j] = true
		q2 = append(q2, [2]int{m - 1, j})
		vis2[m-1][j] = true
	}

	bfs := func(q [][2]int, vis [][]bool) {
		for len(q) > 0 {
			x, y := q[0][0], q[0][1]
			q = q[1:]
			for k := 0; k < 4; k++ {
				nx, ny := x+dirs[k], y+dirs[k+1]
				if nx >= 0 && nx < m && ny >= 0 && ny < n &&
					!vis[nx][ny] && heights[nx][ny] >= heights[x][y] {
					vis[nx][ny] = true
					q = append(q, [2]int{nx, ny})
				}
			}
		}
	}

	bfs(q1, vis1)
	bfs(q2, vis2)

	var ans [][]int
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if vis1[i][j] && vis2[i][j] {
				ans = append(ans, []int{i, j})
			}
		}
	}
	return ans
}
