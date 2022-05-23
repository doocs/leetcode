var dirs = [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

type tree struct {
	height int
	pos    int
}

func cutOffTree(forest [][]int) int {
	row, col := len(forest), len(forest[0])

	bfs := func(start, end int) int {
		q := []int{start}
		vis := make(map[int]bool)
		vis[start] = true
		step := 0
		for n := len(q); n > 0; n = len(q) {
			for i := 0; i < n; i++ {
				state := q[0]
				q = q[1:]
				if state == end {
					return step
				}
				for k := 0; k < 4; k++ {
					x, y := state/col+dirs[k][0], state%col+dirs[k][1]
					nxt := x*col + y
					if x >= 0 && x < row && y >= 0 && y < col && forest[x][y] != 0 && !vis[nxt] {
						q = append(q, nxt)
						vis[nxt] = true
					}
				}
			}
			step++
		}
		return -1
	}

	var trees []tree
	for i := 0; i < row; i++ {
		for j := 0; j < col; j++ {
			if forest[i][j] > 1 {
				trees = append(trees, tree{forest[i][j], i*col + j})
			}
		}
	}
	sort.Slice(trees, func(i, j int) bool {
		return trees[i].height < trees[j].height
	})

	ans, start := 0, 0
	for _, t := range trees {
		end := t.pos
		step := bfs(start, end)
		if step == -1 {
			return -1
		}
		ans += step
		start = end
	}
	return ans
}
