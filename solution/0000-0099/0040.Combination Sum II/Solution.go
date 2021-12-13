func combinationSum2(candidates []int, target int) [][]int {
	var ans [][]int
	var t []int
	sort.Ints(candidates)
	var dfs func(u, s int, t []int)
	dfs = func(u, s int, t []int) {
		if s > target {
			return
		}
		if s == target {
			cp := make([]int, len(t))
			copy(cp, t)
			ans = append(ans, cp)
			return
		}
		for i := u; i < len(candidates); i++ {
			if i > u && candidates[i] == candidates[i-1] {
				continue
			}
			t = append(t, candidates[i])
			dfs(i+1, s+candidates[i], t)
			t = t[:len(t)-1]
		}
	}

	dfs(0, 0, t)
	return ans
}