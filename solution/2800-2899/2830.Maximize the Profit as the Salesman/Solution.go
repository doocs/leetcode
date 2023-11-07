func maximizeTheProfit(n int, offers [][]int) int {
	sort.Slice(offers, func(i, j int) bool { return offers[i][1] < offers[j][1] })
	n = len(offers)
	f := make([]int, n+1)
	g := []int{}
	for _, o := range offers {
		g = append(g, o[1])
	}
	for i := 1; i <= n; i++ {
		j := sort.SearchInts(g, offers[i-1][0])
		f[i] = max(f[i-1], f[j]+offers[i-1][2])
	}
	return f[n]
}