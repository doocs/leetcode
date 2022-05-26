func groupStrings(words []string) []int {
	p := map[int]int{}
	size := map[int]int{}
	mx, n := 0, len(words)
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	union := func(a, b int) {
		if _, ok := p[b]; !ok {
			return
		}
		pa, pb := find(a), find(b)
		if pa == pb {
			return
		}
		p[pa] = pb
		size[pb] += size[pa]
		mx = max(mx, size[pb])
		n--
	}

	for _, word := range words {
		x := 0
		for _, c := range word {
			x |= 1 << (c - 'a')
		}
		p[x] = x
		size[x]++
		mx = max(mx, size[x])
		if size[x] > 1 {
			n--
		}
	}
	for x := range p {
		for i := 0; i < 26; i++ {
			union(x, x^(1<<i))
			if ((x >> i) & 1) != 0 {
				for j := 0; j < 26; j++ {
					if ((x >> j) & 1) == 0 {
						union(x, x^(1<<i)|(1<<j))
					}
				}
			}
		}
	}
	return []int{n, mx}
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}