func maxValue(events [][]int, k int) int {
	sort.Slice(events, func(i, j int) bool { return events[i][0] < events[j][0] })
	n := len(events)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	var dfs func(i, k int) int
	dfs = func(i, k int) int {
		if i >= n || k <= 0 {
			return 0
		}
		if f[i][k] > 0 {
			return f[i][k]
		}
		j := sort.Search(n, func(h int) bool { return events[h][0] > events[i][1] })
		ans := max(dfs(i+1, k), dfs(j, k-1)+events[i][2])
		f[i][k] = ans
		return ans
	}
	return dfs(0, k)
}