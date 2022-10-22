func maxTaxiEarnings(n int, rides [][]int) int64 {
	sort.Slice(rides, func(i, j int) bool { return rides[i][0] < rides[j][0] })
	m := len(rides)
	f := make([]int64, m)
	var dfs func(int) int64
	dfs = func(i int) int64 {
		if i >= m {
			return 0
		}
		if f[i] != 0 {
			return f[i]
		}
		s, e, t := rides[i][0], rides[i][1], rides[i][2]
		j := sort.Search(m, func(k int) bool { return rides[k][0] >= e })
		ans := max(dfs(i+1), dfs(j)+int64(e-s+t))
		f[i] = ans
		return ans
	}
	return dfs(0)
}

func max(a, b int64) int64 {
	if a > b {
		return a
	}
	return b
}