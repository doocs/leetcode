type UnionFind struct {
	p    []int
	size []int
	cnt  int
}

func NewUnionFind(n int) *UnionFind {
	p := make([]int, n)
	size := make([]int, n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	return &UnionFind{p, size, n}
}

func (uf *UnionFind) find(x int) int {
	if uf.p[x] != x {
		uf.p[x] = uf.find(uf.p[x])
	}
	return uf.p[x]
}

func (uf *UnionFind) union(a, b int) bool {
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
	uf.cnt--
	return true
}

var (
	N int
	E [][]int
	K int
)

func check(lim int) bool {
	uf := NewUnionFind(N)

	for _, e := range E {
		u, v, s := e[0], e[1], e[2]
		if s >= lim {
			uf.union(u, v)
		}
	}

	rem := K
	for _, e := range E {
		u, v, s := e[0], e[1], e[2]
		if s*2 >= lim && rem > 0 {
			if uf.union(u, v) {
				rem--
			}
		}
	}

	return uf.cnt == 1
}

func maxStability(n int, edges [][]int, k int) int {
	N = n
	E = edges
	K = k

	uf := NewUnionFind(n)
	mn := int(1e6)

	for _, e := range edges {
		u, v, s, must := e[0], e[1], e[2], e[3]
		if must == 1 {
			if s < mn {
				mn = s
			}
			if !uf.union(u, v) {
				return -1
			}
		}
	}

	for _, e := range edges {
		uf.union(e[0], e[1])
	}

	if uf.cnt > 1 {
		return -1
	}

	l, r := 1, mn
	for l < r {
		mid := (l + r + 1) >> 1
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}

	return l
}
