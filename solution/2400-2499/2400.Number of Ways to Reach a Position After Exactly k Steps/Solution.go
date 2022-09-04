func numberOfWays(startPos int, endPos int, k int) int {
	f := map[int]int{}
	var dfs func(i, k int) int
	dfs = func(i, k int) int {
		if abs(i-endPos) > k {
			return 0
		}
		if k == 0 {
			if i == endPos {
				return 1
			}
			return 0
		}
		if v, ok := f[i*10000+k]; ok {
			return v
		}
		res := dfs(i+1, k-1) + dfs(i-1, k-1)
		res %= 1e9 + 7
		f[i*10000+k] = res
		return res
	}
	return dfs(startPos, k)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}