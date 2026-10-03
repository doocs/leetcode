func jobScheduling(startTime []int, endTime []int, profit []int) int {
	n := len(profit)
	type tuple struct{ s, e, p int }
	jobs := make([]tuple, n)
	for i, p := range profit {
		jobs[i] = tuple{startTime[i], endTime[i], p}
	}
	sort.Slice(jobs, func(i, j int) bool { return jobs[i].s < jobs[j].s })
	f := make([]int, n+1)
	for i := n - 1; i >= 0; i-- {
		j := sort.Search(n, func(k int) bool { return jobs[k].s >= jobs[i].e })
		f[i] = max(f[i+1], jobs[i].p+f[j])
	}
	return f[0]
}
