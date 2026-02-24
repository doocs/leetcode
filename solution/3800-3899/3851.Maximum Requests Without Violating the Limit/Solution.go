func maxRequests(requests [][]int, k int, window int) (ans int) {
	g := make(map[int][]int)
	for _, r := range requests {
		u, t := r[0], r[1]
		g[u] = append(g[u], t)
	}
	for _, ts := range g {
		sort.Ints(ts)

		kept := make([]int, 0)
		head := 0
		deletions := 0

		for _, t := range ts {
			for head < len(kept) && t-kept[head] > window {
				head++
			}
			kept = append(kept, t)
			if len(kept)-head > k {
				kept = kept[:len(kept)-1]
				deletions++
			}
		}

		ans += len(ts) - deletions
	}
	return
}
