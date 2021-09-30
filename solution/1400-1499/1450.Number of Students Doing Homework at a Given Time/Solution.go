func busyStudent(startTime []int, endTime []int, queryTime int) int {
	count, n := 0, len(startTime)
	for i := 0; i < n; i++ {
		if startTime[i] <= queryTime && queryTime <= endTime[i] {
			count++
		}
	}
	return count
}