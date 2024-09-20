func findIntegers(n int) int {
	m := bits.Len(uint(n))
	f := make([][2]int, m)
	for i := range f {
		f[i] = [2]int{-1, -1}
	}
	var dfs func(i, pre int, limit bool) int
	dfs = func(i, pre int, limit bool) int {
		if i < 0 {
			return 1
		}
		if !limit && f[i][pre] != -1 {
			return f[i][pre]
		}
		up := 1
		if limit {
			up = n >> i & 1
		}
		ans := 0
		for j := 0; j <= up; j++ {
			if j == 1 && pre == 1 {
				continue
			}
			ans += dfs(i-1, j, limit && j == up)
		}
		if !limit {
			f[i][pre] = ans
		}
		return ans
	}
	return dfs(m-1, 0, true)
}
