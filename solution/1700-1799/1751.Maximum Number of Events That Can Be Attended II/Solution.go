func maxValue(events [][]int, k int) int {
	sort.Slice(events, func(i, j int) bool { return events[i][1] < events[j][1] })
	n := len(events)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	for i := 1; i <= n; i++ {
		st, val := events[i-1][0], events[i-1][2]
		p := sort.Search(i, func(j int) bool { return events[j][1] >= st })
		for j := 1; j <= k; j++ {
			f[i][j] = max(f[i-1][j], f[p][j-1]+val)
		}
	}
	return f[n][k]
}