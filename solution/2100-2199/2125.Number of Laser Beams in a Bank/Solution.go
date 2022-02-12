func numberOfBeams(bank []string) int {
	ans, last := 0, 0
	for _, b := range bank {
		t := strings.Count(b, "1")
		if t > 0 {
			ans += t * last
			last = t
		}
	}
	return ans
}