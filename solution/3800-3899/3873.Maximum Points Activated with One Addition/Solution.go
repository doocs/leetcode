type UnionFind struct {
	p    map[int64]int64
	size map[int64]int
}

func NewUnionFind() *UnionFind {
	return &UnionFind{
		p:    map[int64]int64{},
		size: map[int64]int{},
	}
}

func (uf *UnionFind) find(x int64) int64 {
	if _, ok := uf.p[x]; !ok {
		uf.p[x] = x
		uf.size[x] = 1
	}
	if uf.p[x] != x {
		uf.p[x] = uf.find(uf.p[x])
	}
	return uf.p[x]
}

func (uf *UnionFind) union(a, b int64) bool {
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

func maxActivated(points [][]int) int {
	uf := NewUnionFind()
	m := int64(3e9)

	for _, p := range points {
		uf.union(int64(p[0]), int64(p[1])+m)
	}

	cnt := map[int64]int{}
	for _, p := range points {
		root := uf.find(int64(p[0]))
		cnt[root]++
	}

	mx1, mx2 := 0, 0
	for _, x := range cnt {
		if mx1 < x {
			mx2 = mx1
			mx1 = x
		} else if mx2 < x {
			mx2 = x
		}
	}

	return mx1 + mx2 + 1
}
