func shortestToChar(s string, c byte) []int {
	n := len(s)
	ans := make([]int, n)
	for i, j := 0, -10000; i < n; i++ {
		if s[i] == c {
			j = i
		}
		ans[i] = i - j
	}
	for i, j := n-1, 10000; i >= 0; i-- {
		if s[i] == c {
			j = i
		}
		if j-i < ans[i] {
			ans[i] = j - i
		}
	}
	return ans
}