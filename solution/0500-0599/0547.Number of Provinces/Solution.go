var p []int

func findCircleNum(isConnected [][]int) int {
	n := len(isConnected)
	p = make([]int, n)
	for i := 1; i < n; i++ {
		p[i] = i
	}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if isConnected[i][j] == 1 {
				p[find(i)] = find(j)
			}
		}
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