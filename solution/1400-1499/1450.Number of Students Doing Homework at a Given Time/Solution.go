func busyStudent(startTime []int, endTime []int, queryTime int) (ans int) {
	for i, x := range startTime {
		if x <= queryTime && queryTime <= endTime[i] {
			ans++
		}
	}
	return
}
