func findAnagrams(s string, p string) []int {
	m, n := len(s), len(p)
	if n > m {
		return []int{}
	}
	ans := make([]int, 0)
	window := make([]int, 26)
	for i := 0; i < n; i++ {
		window[p[i]-'a']++
		window[s[i]-'a']--
	}
	if check(window) {
		ans = append(ans, 0)
	}
	for i := n; i < m; i++ {
		window[s[i]-'a']--
		window[s[i-n]-'a']++
		if check(window) {
			ans = append(ans, i-n+1)
		}
	}
	return ans
}

func check(window []int) bool {
	for _, cnt := range window {
		if cnt != 0 {
			return false
		}
	}
	return true
}
