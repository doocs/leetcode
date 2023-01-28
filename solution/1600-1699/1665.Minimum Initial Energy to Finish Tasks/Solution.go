func minimumEffort(tasks [][]int) (ans int) {
	sort.Slice(tasks, func(i, j int) bool { return tasks[i][0]-tasks[i][1] < tasks[j][0]-tasks[j][1] })
	cur := 0
	for _, task := range tasks {
		a, m := task[0], task[1]
		if cur < m {
			ans += m - cur
			cur = m
		}
		cur -= a
	}
	return
}