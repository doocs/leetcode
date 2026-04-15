func closestTarget(words []string, target string, startIndex int) int {
	n := len(words)
	ans := n
	for i, w := range words {
		if w == target {
			t := i - startIndex
			if t < 0 {
				t = -t
			}
			ans = min(ans, t, n-t)
		}
	}
	if ans == n {
		return -1
	}
	return ans
}
