func lengthOfLongestSubstring(s string) int {
	window := make(map[byte]int)
	n := len(s)
	ans := 0
	left, right := 0, 0
	for right < n {
		ch := s[right]
		right++
		window[ch]++
		for window[ch] > 1 {
			window[s[left]]--
			left++
		}
		ans = max(ans, right-left)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
