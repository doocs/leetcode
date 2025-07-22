func findMinimumTime(strength []int, K int) int {
	n := len(strength)
	f := make([]int, 1<<n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i == 1<<n-1 {
			return 0
		}
		if f[i] != -1 {
			return f[i]
		}
		x := 1 + K*bits.OnesCount(uint(i))
		f[i] = 1 << 30
		for j, s := range strength {
			if i>>j&1 == 0 {
				f[i] = min(f[i], dfs(i|1<<j)+(s+x-1)/x)
			}
		}
		return f[i]
	}
	return dfs(0)
}
