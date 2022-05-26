func findTheLongestSubstring(s string) int {
	pos := make([]int, 32)
	for i := range pos {
		pos[i] = math.MaxInt32
	}
	pos[0] = -1
	vowels := "aeiou"
	state, ans := 0, 0
	for i, c := range s {
		for j, v := range vowels {
			if c == v {
				state ^= (1 << j)
			}
		}
		ans = max(ans, i-pos[state])
		pos[state] = min(pos[state], i)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}