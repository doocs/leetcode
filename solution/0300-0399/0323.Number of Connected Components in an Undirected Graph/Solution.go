func countComponents(n int, edges [][]int) int {
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, e := range edges {
		a, b := e[0], e[1]
		p[find(a)] = find(b)
	}
	ans := 0
	for i := 0; i < n; i++ {
		if i == find(i) {
			ans++
		}
	}
	return ans
}