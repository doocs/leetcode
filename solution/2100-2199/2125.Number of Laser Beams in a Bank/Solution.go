func numberOfBeams(bank []string) (ans int) {
	pre := 0
	for _, row := range bank {
		if cur := strings.Count(row, "1"); cur > 0 {
			ans += pre * cur
			pre = cur
		}
	}
	return
}