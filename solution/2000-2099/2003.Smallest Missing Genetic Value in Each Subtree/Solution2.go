func smallestMissingValueSubtree(parents []int, nums []int) []int {
	n := len(nums)
	g := make([][]int, n)
	idx := -1
	ans := make([]int, n)
	for i, p := range parents {
		if i > 0 {
			g[p] = append(g[p], i)
		}
		if nums[i] == 1 {
			idx = i
		}
		ans[i] = 1
	}
	if idx < 0 {
		return ans
	}
	vis := make([]bool, n)
	has := make([]bool, n+2)
	dfs := func(start int) {
		stk := []int{start}
		for len(stk) > 0 {
			i := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			if vis[i] {
				continue
			}
			vis[i] = true
			if nums[i] < len(has) {
				has[nums[i]] = true
			}
			for _, j := range g[i] {
				stk = append(stk, j)
			}
		}
	}
	for i := 2; idx != -1; idx = parents[idx] {
		dfs(idx)
		for has[i] {
			i++
		}
		ans[idx] = i
	}
	return ans
}
