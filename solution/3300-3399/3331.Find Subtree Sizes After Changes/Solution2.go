func findSubtreeSizes(parent []int, s string) []int {
	n := len(s)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		g[parent[i]] = append(g[parent[i]], i)
	}
	d := [26][]int{}
	ans := make([]int, n)
	stk := [][3]int{{0, -1, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, fa, state := cur[0], cur[1], cur[2]
		idx := int(s[i] - 'a')
		if state == 0 {
			ans[i] = 1
			d[idx] = append(d[idx], i)
			stk = append(stk, [3]int{i, fa, 1})
			for _, j := range g[i] {
				stk = append(stk, [3]int{j, i, 0})
			}
		} else {
			k := fa
			if len(d[idx]) > 1 {
				k = d[idx][len(d[idx])-2]
			}
			if k != -1 {
				ans[k] += ans[i]
			}
			d[idx] = d[idx][:len(d[idx])-1]
		}
	}
	return ans
}
