func numberOfWays(s string) int64 {
	n := len(s)
	cnt0 := strings.Count(s, "0")
	cnt1 := n - cnt0
	c0, c1 := 0, 0
	ans := 0
	for _, c := range s {
		if c == '0' {
			ans += c1 * (cnt1 - c1)
			c0++
		} else {
			ans += c0 * (cnt0 - c0)
			c1++
		}
	}
	return int64(ans)
}