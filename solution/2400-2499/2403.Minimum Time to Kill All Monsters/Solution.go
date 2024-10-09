func minimumTime(power []int) int64 {
	n := len(power)
	f := make([]int64, 1<<n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(mask int) int64
	dfs = func(mask int) int64 {
		if mask == 0 {
			return 0
		}
		if f[mask] != -1 {
			return f[mask]
		}
		f[mask] = 1e18
		gain := 1 + (n - bits.OnesCount(uint(mask)))
		for i, x := range power {
			if mask>>i&1 == 1 {
				f[mask] = min(f[mask], dfs(mask^(1<<i))+int64(x+gain-1)/int64(gain))
			}
		}
		return f[mask]
	}
	return dfs(1<<n - 1)
}
