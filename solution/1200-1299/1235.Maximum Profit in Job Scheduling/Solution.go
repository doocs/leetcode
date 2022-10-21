func jobScheduling(startTime []int, endTime []int, profit []int) int {
	n := len(profit)
	type tuple struct{ s, e, p int }
	jobs := make([]tuple, n)
	for i, p := range profit {
		jobs[i] = tuple{startTime[i], endTime[i], p}
	}
	sort.Slice(jobs, func(i, j int) bool { return jobs[i].e < jobs[j].e })
	dp := make([]int, n+1)
	for i, job := range jobs {
		j := sort.Search(i, func(k int) bool { return jobs[k].e > job.s })
		dp[i+1] = max(dp[i], dp[j]+job.p)
	}
	return dp[n]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}