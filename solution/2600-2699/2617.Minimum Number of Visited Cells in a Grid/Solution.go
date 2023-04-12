func minimumVisitedCells(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dist := make([][]int, m)
	row := make([]hp, m)
	col := make([]hp, n)
	for i := range dist {
		dist[i] = make([]int, n)
		for j := range dist[i] {
			dist[i][j] = -1
		}
	}
	dist[0][0] = 1
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			for len(row[i]) > 0 && grid[i][row[i][0].second]+row[i][0].second < j {
				heap.Pop(&row[i])
			}
			if len(row[i]) > 0 && (dist[i][j] == -1 || row[i][0].first+1 < dist[i][j]) {
				dist[i][j] = row[i][0].first + 1
			}
			for len(col[j]) > 0 && grid[col[j][0].second][j]+col[j][0].second < i {
				heap.Pop(&col[j])
			}
			if len(col[j]) > 0 && (dist[i][j] == -1 || col[j][0].first+1 < dist[i][j]) {
				dist[i][j] = col[j][0].first + 1
			}
			if dist[i][j] != -1 {
				heap.Push(&row[i], pair{dist[i][j], j})
				heap.Push(&col[j], pair{dist[i][j], i})
			}
		}
	}
	return dist[m-1][n-1]
}

type pair struct {
	first  int
	second int
}

type hp []pair

func (a hp) Len() int      { return len(a) }
func (a hp) Swap(i, j int) { a[i], a[j] = a[j], a[i] }
func (a hp) Less(i, j int) bool {
	return a[i].first < a[j].first || (a[i].first == a[j].first && a[i].second < a[j].second)
}
func (a *hp) Push(x interface{}) { *a = append(*a, x.(pair)) }
func (a *hp) Pop() interface{}   { l := len(*a); t := (*a)[l-1]; *a = (*a)[:l-1]; return t }