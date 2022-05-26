func lengthOfLongestSubstring(s string) int {
	chars := make(map[byte]bool)
	res := 0
	for i, j := 0, 0; i < len(s); i++ {
		for chars[s[i]] {
			chars[s[j]] = false
			j++
		}
		chars[s[i]] = true
		res = max(res, i-j+1)
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}