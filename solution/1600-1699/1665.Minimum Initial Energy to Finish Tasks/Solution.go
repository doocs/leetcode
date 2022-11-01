func minimumEffort(tasks [][]int) int {
	sort.Slice(tasks, func(i, j int) bool { return tasks[i][0]-tasks[i][1] < tasks[j][0]-tasks[j][1] })
	var ans, t int
	for _, e := range tasks {
		if t < e[1] {
			ans += e[1] - t
			t = e[1]
		}
		t -= e[0]
	}
	return ans
}