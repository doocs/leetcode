func taskSchedulerII(tasks []int, space int) (ans int64) {
	day := map[int]int64{}
	for _, task := range tasks {
		ans++
		if ans < day[task] {
			ans = day[task]
		}
		day[task] = ans + int64(space) + 1
	}
	return
}