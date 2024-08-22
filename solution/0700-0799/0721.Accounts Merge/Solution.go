type unionFind struct {
	p, size []int
}

func newUnionFind(n int) *unionFind {
	p := make([]int, n)
	size := make([]int, n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	return &unionFind{p, size}
}

func (uf *unionFind) find(x int) int {
	if uf.p[x] != x {
		uf.p[x] = uf.find(uf.p[x])
	}
	return uf.p[x]
}

func (uf *unionFind) union(a, b int) bool {
	pa, pb := uf.find(a), uf.find(b)
	if pa == pb {
		return false
	}
	if uf.size[pa] > uf.size[pb] {
		uf.p[pb] = pa
		uf.size[pa] += uf.size[pb]
	} else {
		uf.p[pa] = pb
		uf.size[pb] += uf.size[pa]
	}
	return true
}

func accountsMerge(accounts [][]string) (ans [][]string) {
	n := len(accounts)
	uf := newUnionFind(n)
	d := make(map[string]int)
	for i := 0; i < n; i++ {
		for _, email := range accounts[i][1:] {
			if j, ok := d[email]; ok {
				uf.union(i, j)
			} else {
				d[email] = i
			}
		}
	}
	g := make(map[int]map[string]struct{})
	for i := 0; i < n; i++ {
		root := uf.find(i)
		if _, ok := g[root]; !ok {
			g[root] = make(map[string]struct{})
		}
		for _, email := range accounts[i][1:] {
			g[root][email] = struct{}{}
		}
	}
	for root, s := range g {
		emails := []string{}
		for email := range s {
			emails = append(emails, email)
		}
		sort.Strings(emails)
		account := append([]string{accounts[root][0]}, emails...)
		ans = append(ans, account)
	}
	return
}