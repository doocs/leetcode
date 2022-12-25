func closetTarget(words []string, target string, startIndex int) int {
	n := len(words)
	ans := n
	for i, w := range words {
		if w == target {
			t := abs(i - startIndex)
			ans = min(ans, min(t, n-t))
		}
	}
	if ans == n {
		return -1
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}