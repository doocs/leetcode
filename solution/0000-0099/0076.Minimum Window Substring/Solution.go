func minWindow(s string, t string) string {
	need := make([]int, 128)
	window := make([]int, 128)
	for i := 0; i < len(t); i++ {
		need[t[i]]++
	}

	m, n := len(s), len(t)
	k, mi, cnt := -1, m+1, 0

	for l, r := 0, 0; r < m; r++ {
		c := s[r]
		if window[c]++; window[c] <= need[c] {
			cnt++
		}
		for cnt == n {
			if r-l+1 < mi {
				mi = r - l + 1
				k = l
			}

			c = s[l]
			if window[c] <= need[c] {
				cnt--
			}
			window[c]--
			l++
		}
	}
	if k < 0 {
		return ""
	}
	return s[k : k+mi]
}
