func longestPalindrome(word1 string, word2 string) (ans int) {
	s := word1 + word2
	n := len(s)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
		f[i][i] = 1
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			if s[i] == s[j] {
				f[i][j] = f[i+1][j-1] + 2
				if i < len(word1) && j >= len(word1) && ans < f[i][j] {
					ans = f[i][j]
				}
			} else {
				f[i][j] = max(f[i+1][j], f[i][j-1])
			}
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