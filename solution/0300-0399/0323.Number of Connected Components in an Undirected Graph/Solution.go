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
	visit := make([]bool, n)
	for i := 0; i < n; i++ {
		visit[i] = false
	}
	for i := 0; i < n; i++ {
		if !visit[find(i)] {
			cnt++
			visit[find(i)] = true
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