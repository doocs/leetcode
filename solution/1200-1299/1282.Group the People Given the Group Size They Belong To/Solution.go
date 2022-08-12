func groupThePeople(groupSizes []int) [][]int {
	n := len(groupSizes)
	g := make([][]int, n+1)
	for i, v := range groupSizes {
		g[v] = append(g[v], i)
	}
	ans := [][]int{}
	for i, v := range g {
		for j := 0; j < len(v); j += i {
			ans = append(ans, v[j:j+i])
		}
	}
	return ans
}