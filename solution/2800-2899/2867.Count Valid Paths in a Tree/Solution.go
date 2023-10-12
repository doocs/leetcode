const mx int = 1e5 + 10

var prime [mx]bool

func init() {
	for i := 2; i < mx; i++ {
		prime[i] = true
	}
	for i := 2; i < mx; i++ {
		if prime[i] {
			for j := i + i; j < mx; j += i {
				prime[j] = false
			}
		}
	}
}

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

func (uf *unionFind) getSize(x int) int {
	return uf.size[uf.find(x)]
}

func countPaths(n int, edges [][]int) (ans int64) {
	uf := newUnionFind(n + 1)
	g := make([][]int, n+1)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
		if !prime[u] && !prime[v] {
			uf.union(u, v)
		}
	}
	for i := 1; i <= n; i++ {
		if prime[i] {
			t := 0
			for _, j := range g[i] {
				if !prime[j] {
					cnt := uf.getSize(j)
					ans += int64(cnt + cnt*t)
					t += cnt
				}
			}
		}
	}
	return
}