func minFlipsMonoIncr(s string) int {
	n := len(s)
	left0, right0 := 0, 0
	for _, c := range s {
		if c == '0' {
			right0++
		}
	}
	ans := min(right0, n-right0)
	for i, c := range s {
		x := 0
		if c == '1' {
			x = 1
		}
		right0 -= x ^ 1
		left0 += x ^ 1
		ans = min(ans, i+1-left0+right0)
	}
	return ans
}