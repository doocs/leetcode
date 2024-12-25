func stoneGameV(stoneValue []int) int {
	n := len(stoneValue)
	s := make([]int, n+1)
	for i, x := range stoneValue {
		s[i+1] = s[i] + x
	}
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(int, int) int
	dfs = func(i, j int) int {
		if i >= j {
			return 0
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		ans, l, r := 0, 0, s[j+1]-s[i]
		for k := i; k < j; k++ {
			l += stoneValue[k]
			r -= stoneValue[k]
			if l < r {
				if ans > l*2 {
					continue
				}
				ans = max(ans, dfs(i, k)+l)
			} else if l > r {
				if ans > r*2 {
					break
				}
				ans = max(ans, dfs(k+1, j)+r)
			} else {
				ans = max(ans, max(dfs(i, k), dfs(k+1, j))+l)
			}
		}
		f[i][j] = ans
		return ans
	}
	return dfs(0, n-1)
}
