func jobScheduling(startTime []int, endTime []int, profit []int) int {
	n := len(profit)
	type tuple struct{ s, e, p int }
	jobs := make([]tuple, n)
	for i, p := range profit {
		jobs[i] = tuple{startTime[i], endTime[i], p}
	}
	sort.Slice(jobs, func(i, j int) bool { return jobs[i].s < jobs[j].s })
	f := make([]int, n)
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] != 0 {
			return f[i]
		}
		j := sort.Search(n, func(j int) bool { return jobs[j].s >= jobs[i].e })
		ans := max(dfs(i+1), jobs[i].p+dfs(j))
		f[i] = ans
		return ans
	}
	return dfs(0)
}