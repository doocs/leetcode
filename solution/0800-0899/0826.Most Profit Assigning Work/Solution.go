func maxProfitAssignment(difficulty []int, profit []int, worker []int) int {
	var job [][2]int
	for i := range difficulty {
		job = append(job, [2]int{difficulty[i], profit[i]})
	}

	sort.SliceStable(job, func(i, j int) bool { return job[i][0] <= job[j][0] })
	sort.Ints(worker)
	i, t, n, res := 0, 0, len(difficulty), 0
	for _, w := range worker {
		for i < n && job[i][0] <= w {
			t = max(t, job[i][1])
			i++
		}
		res += t
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}