func maxDistance(words []string) int {
	n := len(words)
	ans := 0
	for i := 0; i < n; i++ {
		if words[i] != words[0] {
			ans = max(ans, i+1)
		}
		if words[i] != words[n-1] {
			ans = max(ans, n-i)
		}
	}
	return ans
}
