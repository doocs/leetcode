func gardenNoAdj(n int, paths [][]int) []int {
	g := make([][]int, n)
	for _, p := range paths {
		x, y := p[0]-1, p[1]-1
		g[x] = append(g[x], y)
		g[y] = append(g[y], x)
	}
	ans := make([]int, n)
	for x := 0; x < n; x++ {
		used := [5]bool{}
		for _, y := range g[x] {
			used[ans[y]] = true
		}
		for c := 1; c < 5; c++ {
			if !used[c] {
				ans[x] = c
				break
			}
		}
	}
	return ans
}