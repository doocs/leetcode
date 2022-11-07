func longestPalindromeSubseq(s string) int {
	n := len(s)
	f := make([][][]int, n)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, 27)
			for k := range f[i][j] {
				f[i][j][k] = -1
			}
		}
	}
	var dfs func(i, j, x int) int
	dfs = func(i, j, x int) int {
		if i >= j {
			return 0
		}
		if f[i][j][x] != -1 {
			return f[i][j][x]
		}
		ans := 0
		if s[i] == s[j] && int(s[i]-'a') != x {
			ans = dfs(i+1, j-1, int(s[i]-'a')) + 2
		} else {
			ans = max(dfs(i+1, j, x), dfs(i, j-1, x))
		}
		f[i][j][x] = ans
		return ans
	}
	return dfs(0, n-1, 26)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}