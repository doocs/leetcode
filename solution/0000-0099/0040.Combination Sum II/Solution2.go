func combinationSum2(candidates []int, target int) (ans [][]int) {
	sort.Ints(candidates)
	t := []int{}
	var dfs func(i, s int)
	dfs = func(i, s int) {
		if s == 0 {
			ans = append(ans, slices.Clone(t))
			return
		}
		if i >= len(candidates) || s < candidates[i] {
			return
		}
		x := candidates[i]
		t = append(t, x)
		dfs(i+1, s-x)
		t = t[:len(t)-1]
		for i < len(candidates) && candidates[i] == x {
			i++
		}
		dfs(i, s)
	}
	dfs(0, target)
	return
}
