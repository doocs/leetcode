func baseUnitConversions(conversions [][]int) []int {
	const mod = int(1e9 + 7)
	n := len(conversions) + 1

	g := make([][]struct{ t, w int }, n)
	for _, e := range conversions {
		s, t, w := e[0], e[1], e[2]
		g[s] = append(g[s], struct{ t, w int }{t, w})
	}

	ans := make([]int, n)
	stk := [][2]int{{0, 1}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		s, mul := cur[0], cur[1]
		ans[s] = mul
		for _, e := range g[s] {
			stk = append(stk, [2]int{e.t, mul * e.w % mod})
		}
	}
	return ans
}
