func numKLenSubstrNoRepeats(s string, k int) int {
	mp := make(map[rune]int)
	ans, j := 0, 0
	for i, c := range s {
		if v, ok := mp[c]; ok {
			j = max(j, v+1)
		}
		mp[c] = i
		if i-j+1 >= k {
			ans++
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}