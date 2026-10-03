func maxTaxiEarnings(n int, rides [][]int) int64 {
	sort.Slice(rides, func(i, j int) bool { return rides[i][0] < rides[j][0] })
	m := len(rides)
	f := make([]int64, m+1)
	for i := m - 1; i >= 0; i-- {
		st, ed, tip := rides[i][0], rides[i][1], rides[i][2]
		j := sort.Search(m, func(k int) bool { return rides[k][0] >= ed })
		f[i] = max(f[i+1], int64(ed-st+tip)+f[j])
	}
	return f[0]
}
