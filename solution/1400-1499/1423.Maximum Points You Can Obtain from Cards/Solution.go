func maxScore(cardPoints []int, k int) int {
	n := len(cardPoints)
	s := make([]int, n+1)
	for i := 0; i < n; i++ {
		s[i+1] = s[i] + cardPoints[i]
	}
	mi := math.MaxInt64
	for i := 0; i < n; i++ {
		j := i + (n - k) - 1
		if j < n {
			mi = min(mi, s[j+1]-s[i])
		}
	}
	return s[n] - mi
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}