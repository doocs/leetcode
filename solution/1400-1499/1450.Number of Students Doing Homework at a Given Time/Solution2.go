func busyStudent(startTime []int, endTime []int, queryTime int) int {
	c := make([]int, 1010)
	for i, a := range startTime {
		b := endTime[i]
		c[a]++
		c[b+1]--
	}
	ans := 0
	for i := 0; i <= queryTime; i++ {
		ans += c[i]
	}
	return ans
}