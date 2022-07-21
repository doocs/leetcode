func minimumTime(jobs []int, workers []int) int {
	sort.Ints(jobs)
	sort.Ints(workers)
	ans := 0
	for i, a := range jobs {
		b := workers[i]
		ans = max(ans, (a+b-1)/b)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}