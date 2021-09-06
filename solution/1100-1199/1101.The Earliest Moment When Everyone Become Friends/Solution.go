var p []int

func earliestAcq(logs [][]int, n int) int {
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	sort.Slice(logs, func(i, j int) bool {
		return logs[i][0] < logs[j][0]
	})
	for _, log := range logs {
		a, b := log[1], log[2]
		pa, pb := find(a), find(b)
		if pa == pb {
			continue
		}
		p[pa] = pb
		n--
		if n == 1 {
			return log[0]
		}
	}
	return -1
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}