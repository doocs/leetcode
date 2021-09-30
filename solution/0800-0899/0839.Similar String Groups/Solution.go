var p []int

func numSimilarGroups(strs []string) int {
	n := len(strs)
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			if !check(strs[i], strs[j]) {
				continue
			}
			p[find(i)] = find(j)
		}
	}
	res := 0
	for i := 0; i < n; i++ {
		if i == find(i) {
			res++
		}
	}
	return res
}

func check(a, b string) bool {
	cnt, n := 0, len(a)
	for i := 0; i < n; i++ {
		if a[i] != b[i] {
			cnt++
		}
	}
	return cnt <= 2
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}