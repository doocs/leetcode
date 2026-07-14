func secondsBetweenTimes(startTime string, endTime string) int {
	return f(endTime) - f(startTime)
}

func f(s string) int {
	h, _ := strconv.Atoi(s[:2])
	m, _ := strconv.Atoi(s[3:5])
	sec, _ := strconv.Atoi(s[6:])
	return h*3600 + m*60 + sec
}
