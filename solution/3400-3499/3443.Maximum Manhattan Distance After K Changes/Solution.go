func maxDistance(s string, k int) int {
	calc := func(a rune, b rune) int {
		var ans, mx, cnt int
		for _, c := range s {
			if c == a || c == b {
				mx++
			} else if cnt < k {
				mx++
				cnt++
			} else {
				mx--
			}
			ans = max(ans, mx)
		}
		return ans
	}
	a := calc('S', 'E')
	b := calc('S', 'W')
	c := calc('N', 'E')
	d := calc('N', 'W')
	return max(a, b, c, d)
}
