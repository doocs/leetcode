func bestClosingTime(customers string) (ans int) {
	n := len(customers)
	s := make([]int, n+1)
	for i, c := range customers {
		s[i+1] = s[i]
		if c == 'Y' {
			s[i+1]++
		}
	}
	cost := 1 << 30
	for j := 0; j <= n; j++ {
		t := j - s[j] + s[n] - s[j]
		if cost > t {
			ans, cost = j, t
		}
	}
	return
}