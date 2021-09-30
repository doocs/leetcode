var p []int

func findCircleNum(isConnected [][]int) int {
	n := len(isConnected)
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	size := n
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if isConnected[i][j] == 1 && find(i) != find(j) {
				p[find(i)] = find(j)
				size--
			}
		}
	}
	return size
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}