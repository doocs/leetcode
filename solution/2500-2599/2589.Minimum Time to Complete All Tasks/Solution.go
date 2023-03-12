func findMinimumTime(tasks [][]int) (ans int) {
	sort.Slice(tasks, func(i, j int) bool { return tasks[i][1] < tasks[j][1] })
	vis := [2010]int{}
	for _, task := range tasks {
		start, end, duration := task[0], task[1], task[2]
		for _, x := range vis[start : end+1] {
			duration -= x
		}
		for i := end; i >= start && duration > 0; i-- {
			if vis[i] == 0 {
				vis[i] = 1
				duration--
				ans++
			}
		}
	}
	return
}