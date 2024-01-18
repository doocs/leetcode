func findCircleNum(isConnected [][]int) int {
	n := len(isConnected)
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
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			if isConnected[i][j] == 1 {
				p[find(i)] = find(j)
			}
		}
	}
	ans := 0
	for i := range p {
		if p[i] == i {
			ans++
		}
	}
	return ans
}