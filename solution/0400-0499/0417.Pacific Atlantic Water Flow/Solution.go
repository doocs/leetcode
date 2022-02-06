func pacificAtlantic(heights [][]int) [][]int {
	m, n := len(heights), len(heights[0])
	vis1 := make(map[int]bool)
	vis2 := make(map[int]bool)
	var q1 [][]int
	var q2 [][]int
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if i == 0 || j == 0 {
				vis1[i*n+j] = true
				q1 = append(q1, []int{i, j})
			}
			if i == m-1 || j == n-1 {
				vis2[i*n+j] = true
				q2 = append(q2, []int{i, j})
			}
		}
	}
	dirs := []int{-1, 0, 1, 0, -1}
	bfs := func(q [][]int, vis map[int]bool) {
		for len(q) > 0 {
			for k := len(q); k > 0; k-- {
				p := q[0]
				q = q[1:]
				for i := 0; i < 4; i++ {
					x, y := p[0]+dirs[i], p[1]+dirs[i+1]
					if x >= 0 && x < m && y >= 0 && y < n && !vis[x*n+y] && heights[x][y] >= heights[p[0]][p[1]] {
						vis[x*n+y] = true
						q = append(q, []int{x, y})
					}
				}
			}
		}
	}
	bfs(q1, vis1)
	bfs(q2, vis2)
	var ans [][]int
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			x := i*n + j
			if vis1[x] && vis2[x] {
				ans = append(ans, []int{i, j})
			}
		}
	}
	return ans
}