var dirs = [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

type tree struct {
	height int
	pos    int
}

func cutOffTree(forest [][]int) int {
	row, col := len(forest), len(forest[0])
	f := func(a, b int) int {
		return abs(a/col-b/col) + abs(a%col-b%col)
	}
	bfs := func(start, end int) int {
		q := hp{{f(start, end), start}}
		heap.Init(&q)
		dist := map[int]int{start: 0}
		for q.Len() > 0 {
			state := heap.Pop(&q).(pair).pos
			if state == end {
				return dist[state]
			}
			step := dist[state]
			for k := 0; k < 4; k++ {
				x, y := state/col+dirs[k][0], state%col+dirs[k][1]
				if x >= 0 && x < row && y >= 0 && y < col && forest[x][y] != 0 {
					nxt := x*col + y
					if d, ok := dist[nxt]; !ok || d > step+1 {
						dist[nxt] = step + 1
						heap.Push(&q, pair{dist[nxt] + f(nxt, end), nxt})
					}
				}
			}
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
		step := bfs(start, t.pos)
		if step == -1 {
			return -1
		}
		ans += step
		start = t.pos
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

type pair struct{ prio, pos int }
type hp []pair

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].prio < h[j].prio }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(pair)) }
func (h *hp) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
