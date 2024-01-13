func combinationSum(candidates []int, target int) (ans [][]int) {
	sort.Ints(candidates)
	t := []int{}
	var dfs func(i, s int)
	dfs = func(i, s int) {
		if s == 0 {
			ans = append(ans, slices.Clone(t))
			return
		}
		if s < candidates[i] {
			return
		}
		for j := i; j < len(candidates); j++ {
			t = append(t, candidates[j])
			dfs(j, s-candidates[j])
			t = t[:len(t)-1]
		}
	}
	dfs(0, target)
	return
}