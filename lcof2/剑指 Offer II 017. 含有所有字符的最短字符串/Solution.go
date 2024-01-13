func minWindow(s string, t string) string {
	m, n := len(s), len(t)
	if n > m {
		return ""
	}
	need, window := make(map[byte]int), make(map[byte]int)
	for _, r := range t {
		need[byte(r)]++
	}
	start, minLen := 0, math.MaxInt32
	left, right := 0, 0
	for right < m {
		window[s[right]]++
		right++
		for check(need, window) {
			if right-left < minLen {
				minLen = right - left
				start = left
			}
			window[s[left]]--
			left++
		}
	}
	if minLen == math.MaxInt32 {
		return ""
	}
	return s[start : start+minLen]
}

func check(need, window map[byte]int) bool {
	for k, v := range need {
		if window[k] < v {
			return false
		}
	}
	return true
}