func combinationSum2(candidates []int, target int) (ans [][]int) {
	sort.Ints(candidates)
	t := []int{}
	var dfs func(i, s int)
	dfs = func(i, s int) {
		if s == 0 {
			cp := make([]int, len(t))
			copy(cp, t)
			ans = append(ans, cp)
			return
		}
		if i >= len(candidates) || s < candidates[i] {
			return
		}
		for j := i; j < len(candidates); j++ {
			if j > i && candidates[j] == candidates[j-1] {
				continue
			}
			t = append(t, candidates[j])
			dfs(j+1, s-candidates[j])
			t = t[:len(t)-1]
		}
	}
	dfs(0, target)
	return
}