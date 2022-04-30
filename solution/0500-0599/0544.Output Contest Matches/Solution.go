func findContestMatch(n int) string {
	team := make([]string, n)
	for i := range team {
		team[i] = strconv.Itoa(i + 1)
	}
	for n > 1 {
		for i := 0; i < n>>1; i++ {
			team[i] = "(" + team[i] + "," + team[n-1-i] + ")"
		}
		n >>= 1
	}
	return team[0]
}