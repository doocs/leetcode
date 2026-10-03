func maxValue(events [][]int, k int) int {
	sort.Slice(events, func(i, j int) bool { return events[i][0] < events[j][0] })
	n := len(events)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	for i := n - 1; i >= 0; i-- {
		ed, val := events[i][1], events[i][2]
		j := sort.Search(n, func(h int) bool { return events[h][0] > ed })
		for c := 0; c <= k; c++ {
			f[i][c] = f[i+1][c]
			if c > 0 {
				f[i][c] = max(f[i][c], f[j][c-1]+val)
			}
		}
	}
	return f[0][k]
}
