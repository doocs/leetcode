func countSubstrings(s string) int {
	t := "^#"
	for _, c := range s {
		t += string(c)
		t += "#"
	}
	t += "$"

	n := len(t)
	p := make([]int, n)
	pos, maxRight := 0, 0
	ans := 0

	for i := 1; i < n-1; i++ {
		if maxRight > i {
			mirror := 2*pos - i
			if p[mirror] < maxRight-i {
				p[i] = p[mirror]
			} else {
				p[i] = maxRight - i
			}
		} else {
			p[i] = 1
		}

		for t[i-p[i]] == t[i+p[i]] {
			p[i]++
		}

		if i+p[i] > maxRight {
			maxRight = i + p[i]
			pos = i
		}

		ans += p[i] / 2
	}

	return ans
}
