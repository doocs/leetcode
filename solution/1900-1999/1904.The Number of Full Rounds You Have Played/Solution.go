func numberOfRounds(loginTime string, logoutTime string) int {
	f := func(s string) int {
		var h, m int
		fmt.Sscanf(s, "%d:%d", &h, &m)
		return h*60 + m
	}
	a, b := f(loginTime), f(logoutTime)
	if a > b {
		b += 1440
	}
	return max(0, b/15-(a+14)/15)
}