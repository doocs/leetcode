func minProcessingTime(processorTime []int, tasks []int) (ans int) {
	sort.Ints(processorTime)
	sort.Ints(tasks)
	i := len(tasks) - 1
	for _, t := range processorTime {
		ans = max(ans, t+tasks[i])
		i -= 4
	}
	return
}