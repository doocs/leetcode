var p []int

func countComponents(n int, edges [][]int) int {
	p = make([]int, n)
	for i := 1; i < n; i++ {
		p[i] = i
	}
	for _, e := range edges {
		a, b := e[0], e[1]
		p[find(b)] = find(a)
	}
	cnt := 0
	for i := 0; i < n; i++ {
		if i == find(i) {
			cnt++
		}
	}
	return cnt
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}