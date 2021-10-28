func partitionLabels(s string) []int {
	n := len(s)
	last := make([]int, 128)
	for i := 0; i < n; i++ {
		last[s[i]] = i
	}
	var ans []int
	left, right := 0, 0
	for i := 0; i < n; i++ {
		right = max(right, last[s[i]])
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