func maxTaxiEarnings(n int, rides [][]int) int64 {
	sort.Slice(rides, func(i, j int) bool { return rides[i][1] < rides[j][1] })
	m := len(rides)
	f := make([]int64, m+1)
	for i := 1; i <= m; i++ {
		r := rides[i-1]
		st, ed, tip := r[0], r[1], r[2]
		j := sort.Search(m, func(j int) bool { return rides[j][1] >= st+1 })
		f[i] = max(f[i-1], f[j]+int64(ed-st+tip))
	}
	return f[m]
}