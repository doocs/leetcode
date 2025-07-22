type Queue []string

func (q *Queue) Push(s string) {
	*q = append(*q, s)
}

func (q *Queue) Pop() string {
	s := (*q)[0]
	*q = (*q)[1:]
	return s
}

func (q *Queue) Empty() bool {
	return len(*q) == 0
}

func minimumMoves(grid [][]int) int {
	q := Queue{f(grid)}
	vis := map[string]bool{f(grid): true}
	dirs := []int{-1, 0, 1, 0, -1}

	for ans := 0; ; ans++ {
		sz := len(q)
		for ; sz > 0; sz-- {
			p := q.Pop()
			if p == "111111111" {
				return ans
			}
			cur := g(p)

			for i := 0; i < 3; i++ {
				for j := 0; j < 3; j++ {
					if cur[i][j] > 1 {
						for d := 0; d < 4; d++ {
							x, y := i+dirs[d], j+dirs[d+1]
							if x >= 0 && x < 3 && y >= 0 && y < 3 && cur[x][y] < 2 {
								nxt := make([][]int, 3)
								for r := range nxt {
									nxt[r] = append([]int(nil), cur[r]...)
								}
								nxt[i][j]--
								nxt[x][y]++
								s := f(nxt)
								if !vis[s] {
									vis[s] = true
									q.Push(s)
								}
							}
						}
					}
				}
			}
		}
	}
}

func f(grid [][]int) string {
	var sb strings.Builder
	for _, row := range grid {
		for _, x := range row {
			sb.WriteByte(byte(x) + '0')
		}
	}
	return sb.String()
}

func g(s string) [][]int {
	grid := make([][]int, 3)
	for i := range grid {
		grid[i] = make([]int, 3)
		for j := 0; j < 3; j++ {
			grid[i][j] = int(s[i*3+j] - '0')
		}
	}
	return grid
}