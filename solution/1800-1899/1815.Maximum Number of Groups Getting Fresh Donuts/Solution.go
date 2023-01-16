func maxHappyGroups(batchSize int, groups []int) (ans int) {
	state := 0
	for _, v := range groups {
		i := v % batchSize
		if i == 0 {
			ans++
		} else {
			state += 1 << (i * 5)
		}
	}
	f := map[int]int{}
	var dfs func(int, int) int
	dfs = func(state, mod int) int {
		if v, ok := f[state]; ok {
			return v
		}
		res := 0
		x := 0
		if mod == 0 {
			x = 1
		}
		for i := 1; i < batchSize; i++ {
			if state>>(i*5)&31 != 0 {
				t := dfs(state-1<<(i*5), (mod+i)%batchSize)
				res = max(res, t+x)
			}
		}
		f[state] = res
		return res
	}
	ans += dfs(state, 0)
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}