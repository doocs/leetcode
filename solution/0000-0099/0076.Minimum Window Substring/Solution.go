func minWindow(s string, t string) string {
	ans := ""
	m, n := len(s), len(t)
	if m < n {
		return ans
	}
	need := make([]int, 128)
	for _, c := range t {
		need[c] += 1
	}
	window := make([]int, 128)
	i, cnt, mi := 0, 0, m+1
	for j, c := range s {
		window[c]++
		if need[c] >= window[c] {
			cnt++
		}
		for cnt == n {
			if j-i+1 < mi {
				mi = j - i + 1
				ans = s[i : j+1]
			}
			c = rune(s[i])
			if need[c] >= window[c] {
				cnt--
			}
			window[c]--
			i++
		}
	}
	return ans
}