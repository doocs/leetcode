func countSubTrees(n int, edges [][]int, labels string) []int {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	ans := make([]int, n)
	cnt := [26]int{}
	stk := [][3]int{{0, -1, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, fa, state := cur[0], cur[1], cur[2]
		k := labels[i] - 'a'
		if state == 0 {
			ans[i] -= cnt[k]
			cnt[k]++
			stk = append(stk, [3]int{i, fa, 1})
			for _, j := range g[i] {
				if j != fa {
					stk = append(stk, [3]int{j, i, 0})
				}
			}
		} else {
			ans[i] += cnt[k]
		}
	}
	return ans
}
