func earliestTime(tasks [][]int) int {
	ans := 200
	for _, task := range tasks {
		ans = min(ans, task[0]+task[1])
	}
	return ans
}
