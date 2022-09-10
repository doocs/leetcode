func minimumTime(power []int) int64 {
	n := len(power)
	f := make([]int64, 1<<n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(mask int) int64
	dfs = func(mask int) int64 {
		if f[mask] != -1 {
			return f[mask]
		}
		cnt := bits.OnesCount(uint(mask))
		if cnt == n {
			return 0
		}
		var ans int64 = math.MaxInt64
		for i, v := range power {
			if (mask >> i & 1) == 1 {
				continue
			}
			ans = min(ans, dfs(mask|1<<i)+int64((v+cnt)/(cnt+1)))
		}
		f[mask] = ans
		return ans
	}
	return dfs(0)
}

func min(a, b int64) int64 {
	if a < b {
		return a
	}
	return b
}