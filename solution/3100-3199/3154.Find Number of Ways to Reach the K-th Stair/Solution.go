func waysToReachStair(k int) int {
	f := map[int]int{}
	var dfs func(i, j, jump int) int
	dfs = func(i, j, jump int) int {
		if i > k+1 {
			return 0
		}
		key := (i << 32) | jump<<1 | j
		if v, has := f[key]; has {
			return v
		}
		ans := 0
		if i == k {
			ans++
		}
		if i > 0 && j == 0 {
			ans += dfs(i-1, 1, jump)
		}
		ans += dfs(i+(1<<jump), 0, jump+1)
		f[key] = ans
		return ans
	}
	return dfs(1, 0, 0)
}