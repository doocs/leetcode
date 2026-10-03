func componentValue(nums []int, edges [][]int) int {
	s, mx := 0, slices.Max(nums)
	for _, x := range nums {
		s += x
	}
	n := len(nums)
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	check := func(t int) bool {
		sz := make([]int, n)
		stk := [][3]int{{0, -1, 0}}
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
				x := nums[i]
				for _, j := range g[i] {
					if j != fa {
						x += sz[j]
					}
				}
				if x > t {
					return false
				}
				if x == t {
					sz[i] = 0
				} else {
					sz[i] = x
				}
			}
		}
		return sz[0] == 0
	}
	for k := min(n, s/mx); k > 1; k-- {
		if s%k == 0 && check(s/k) {
			return k - 1
		}
	}
	return 0
}
