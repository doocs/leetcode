func minimumScore(nums []int, edges [][]int) int {
	n := len(nums)
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	s := 0
	for _, x := range nums {
		s ^= x
	}
	componentXor := func(root, ban int) int {
		sub := make([]int, n)
		stk := [][3]int{{root, ban, 0}}
		for len(stk) > 0 {
			cur := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			i, fa, state := cur[0], cur[1], cur[2]
			if state == 0 {
				stk = append(stk, [3]int{i, fa, 1})
				for _, j := range g[i] {
					if j != fa {
						stk = append(stk, [3]int{j, i, 0})
					}
				}
			} else {
				res := nums[i]
				for _, j := range g[i] {
					if j != fa {
						res ^= sub[j]
					}
				}
				sub[i] = res
			}
		}
		return sub[root]
	}
	collect := func(root, ban, s1 int) int {
		ans := math.MaxInt32
		sub := make([]int, n)
		stk := [][3]int{{root, ban, 0}}
		for len(stk) > 0 {
			cur := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			i, fa, state := cur[0], cur[1], cur[2]
			if state == 0 {
				stk = append(stk, [3]int{i, fa, 1})
				for _, j := range g[i] {
					if j != fa {
						stk = append(stk, [3]int{j, i, 0})
					}
				}
			} else {
				res := nums[i]
				for _, j := range g[i] {
					if j != fa {
						s2 := sub[j]
						res ^= s2
						mx := max(s^s1, s2, s1^s2)
						mn := min(s^s1, s2, s1^s2)
						ans = min(ans, mx-mn)
					}
				}
				sub[i] = res
			}
		}
		return ans
	}
	ans := math.MaxInt32
	for i := 0; i < n; i++ {
		for _, j := range g[i] {
			s1 := componentXor(i, j)
			ans = min(ans, collect(i, j, s1))
		}
	}
	return ans
}
