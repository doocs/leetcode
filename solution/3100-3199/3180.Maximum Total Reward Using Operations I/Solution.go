func maxTotalReward(rewardValues []int) int {
	sort.Ints(rewardValues)
	n := len(rewardValues)
	f := make([]int, rewardValues[n-1]<<1)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(x int) int {
		if f[x] != -1 {
			return f[x]
		}
		i := sort.SearchInts(rewardValues, x+1)
		f[x] = 0
		for _, v := range rewardValues[i:] {
			f[x] = max(f[x], v+dfs(x+v))
		}
		return f[x]
	}
	return dfs(0)
}