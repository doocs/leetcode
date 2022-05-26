func countBinarySubstrings(s string) int {
	i, n := 0, len(s)
	var t []int
	for i < n {
		cnt := 1
		for i+1 < n && s[i+1] == s[i] {
			i++
			cnt++
		}
		t = append(t, cnt)
		i++
	}
	ans := 0
	for i := 1; i < len(t); i++ {
		ans += min(t[i-1], t[i])
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}