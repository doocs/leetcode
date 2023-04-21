func minWindow(s string, t string) string {
	need := [128]int{}
	window := [128]int{}
	for _, c := range t {
		need[c]++
	}
	cnt, j, k, mi := 0, 0, -1, 1<<30
	for i, c := range s {
		window[c]++
		if need[c] >= window[c] {
			cnt++
		}
		for cnt == len(t) {
			if i-j+1 < mi {
				mi = i - j + 1
				k = j
			}
			if need[s[j]] >= window[s[j]] {
				cnt--
			}
			window[s[j]]--
			j++
		}
	}
	if k < 0 {
		return ""
	}
	return s[k : k+mi]
}