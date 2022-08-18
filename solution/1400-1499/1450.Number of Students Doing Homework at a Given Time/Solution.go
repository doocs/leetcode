func busyStudent(startTime []int, endTime []int, queryTime int) int {
	ans := 0
	for i, a := range startTime {
		b := endTime[i]
		if a <= queryTime && queryTime <= b {
			ans++
		}
	}
	return ans
}