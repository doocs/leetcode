func longestPalindromicSubsequence(s string, k int) int {
	n := len(s)
	f := make([][][]int, n)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, k+1)
			for l := range f[i][j] {
				f[i][j][l] = -1
			}
		}
	}
	var dfs func(int, int, int) int
	dfs = func(i, j, k int) int {
		if i > j {
			return 0
		}
		if i == j {
			return 1
		}
		if f[i][j][k] != -1 {
			return f[i][j][k]
		}
		res := max(dfs(i+1, j, k), dfs(i, j-1, k))
		d := abs(int(s[i]) - int(s[j]))
		t := min(d, 26-d)
		if t <= k {
			res = max(res, 2+dfs(i+1, j-1, k-t))
		}
		f[i][j][k] = res
		return res
	}
	return dfs(0, n-1, k)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
