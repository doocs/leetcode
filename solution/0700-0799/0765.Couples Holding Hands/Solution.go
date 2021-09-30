var p []int

func minSwapsCouples(row []int) int {
	n := len(row) >> 1
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	for i := 0; i < len(row); i += 2 {
		a, b := row[i]>>1, row[i+1]>>1
		p[find(a)] = find(b)
	}
	cnt := 0
	for i := 0; i < n; i++ {
		if i == find(i) {
			cnt++
		}
	}
	return n - cnt
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}