func numOfSubsequences(s string) int64 {
	calc := func(t string) int64 {
		cnt, a := int64(0), int64(0)
		for _, c := range s {
			if c == rune(t[1]) {
				cnt += a
			}
			if c == rune(t[0]) {
				a++
			}
		}
		return cnt
	}

	l, r := int64(0), int64(0)
	for _, c := range s {
		if c == 'T' {
			r++
		}
	}

	ans, mx := int64(0), int64(0)
	for _, c := range s {
		if c == 'T' {
			r--
		}
		if c == 'C' {
			ans += l * r
		}
		if c == 'L' {
			l++
		}
		mx = max(mx, l*r)
	}
	mx = max(mx, calc("LC"), calc("CT"))
	ans += mx
	return ans
}