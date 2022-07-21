func idealArrays(n int, maxValue int) int {
	mod := int(1e9) + 7
	m := maxValue
	c := make([][]int, n)
	f := make([][]int, m+1)
	for i := range c {
		c[i] = make([]int, 16)
	}
	for i := range f {
		f[i] = make([]int, 16)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(int, int) int
	dfs = func(i, cnt int) int {
		if f[i][cnt] != -1 {
			return f[i][cnt]
		}
		res := c[n-1][cnt-1]
		if cnt < n {
			for k := 2; k*i <= m; k++ {
				res = (res + dfs(k*i, cnt+1)) % mod
			}
		}
		f[i][cnt] = res
		return res
	}
	for i := 0; i < n; i++ {
		for j := 0; j <= i && j < 16; j++ {
			if j == 0 {
				c[i][j] = 1
			} else {
				c[i][j] = (c[i-1][j] + c[i-1][j-1]) % mod
			}
		}
	}
	ans := 0
	for i := 1; i <= m; i++ {
		ans = (ans + dfs(i, 1)) % mod
	}
	return ans
}