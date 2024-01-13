func findLatestStep(arr []int, m int) int {
	n := len(arr)
	if m == n {
		return n
	}
	p := make([]int, n)
	size := make([]int, n)
	vis := make([]bool, n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	union := func(a, b int) {
		pa, pb := find(a), find(b)
		if pa == pb {
			return
		}
		p[pa] = pb
		size[pb] += size[pa]
	}

	ans := -1
	for i, v := range arr {
		v--
		if v > 0 && vis[v-1] {
			if size[find(v-1)] == m {
				ans = i
			}
			union(v, v-1)
		}
		if v < n-1 && vis[v+1] {
			if size[find(v+1)] == m {
				ans = i
			}
			union(v, v+1)
		}
		vis[v] = true
	}
	return ans
}