type pair struct{ x, y int }

func minMoves(matrix []string) int {
	m, n := len(matrix), len(matrix[0])
	g := make(map[rune][]pair)
	for i := 0; i < m; i++ {
		for j, c := range matrix[i] {
			if unicode.IsLetter(c) {
				g[c] = append(g[c], pair{i, j})
			}
		}
	}
	dirs := []int{-1, 0, 1, 0, -1}
	INF := 1 << 30
	dist := make([][]int, m)
	for i := range dist {
		dist[i] = make([]int, n)
		for j := range dist[i] {
			dist[i][j] = INF
		}
	}
	dist[0][0] = 0
	q := list.New()
	q.PushBack(pair{0, 0})
	for q.Len() > 0 {
		cur := q.Remove(q.Front()).(pair)
		i, j := cur.x, cur.y
		d := dist[i][j]
		if i == m-1 && j == n-1 {
			return d
		}
		c := rune(matrix[i][j])
		if v, ok := g[c]; ok {
			for _, p := range v {
				x, y := p.x, p.y
				if d < dist[x][y] {
					dist[x][y] = d
					q.PushFront(pair{x, y})
				}
			}
			delete(g, c)
		}
		for idx := 0; idx < 4; idx++ {
			x, y := i+dirs[idx], j+dirs[idx+1]
			if 0 <= x && x < m && 0 <= y && y < n && matrix[x][y] != '#' && d+1 < dist[x][y] {
				dist[x][y] = d + 1
				q.PushBack(pair{x, y})
			}
		}
	}
	return -1
}
