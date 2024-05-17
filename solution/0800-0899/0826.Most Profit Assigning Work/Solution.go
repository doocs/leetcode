func maxProfitAssignment(difficulty []int, profit []int, worker []int) (ans int) {
	sort.Ints(worker)
	n := len(profit)
	jobs := make([][2]int, n)
	for i, p := range profit {
		jobs[i] = [2]int{difficulty[i], p}
	}
	sort.Slice(jobs, func(i, j int) bool { return jobs[i][0] < jobs[j][0] })
	mx, i := 0, 0
	for _, w := range worker {
		for ; i < n && jobs[i][0] <= w; i++ {
			mx = max(mx, jobs[i][1])
		}
		ans += mx
	}
	return
}