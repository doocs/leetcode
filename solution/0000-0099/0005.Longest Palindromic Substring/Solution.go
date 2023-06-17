func longestPalindrome(s string) string {
	n := len(s)
	f := make([][]bool, n)
	for i := range f {
		f[i] = make([]bool, n)
		for j := range f[i] {
			f[i][j] = true
		}
	}
	k, mx := 0, 1
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			f[i][j] = false
			if s[i] == s[j] {
				f[i][j] = f[i+1][j-1]
				if f[i][j] && mx < j-i+1 {
					mx = j - i + 1
					k = i
				}
			}
		}
	}
	return s[k : k+mx]
}