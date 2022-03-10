func partitionLabels(s string) []int {
	last := make([]int, 26)
	n := len(s)
	for i := 0; i < n; i++ {
		last[s[i]-'a'] = i
	}
	var ans []int
	for i, left, right := 0, 0, 0; i < n; i++ {
		right = max(right, last[s[i]-'a'])
		if i == right {
			ans = append(ans, right-left+1)
			left = right + 1
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