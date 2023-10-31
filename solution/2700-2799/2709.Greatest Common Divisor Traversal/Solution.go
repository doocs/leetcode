const mx = 100010

var p = make([][]int, mx)

func init() {
	for x := 1; x < mx; x++ {
		v := x
		i := 2
		for i <= v/i {
			if v%i == 0 {
				p[x] = append(p[x], i)
				for v%i == 0 {
					v /= i
				}
			}
			i++
		}
		if v > 1 {
			p[x] = append(p[x], v)
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

func canTraverseAllPairs(nums []int) bool {
	m := 0
	for _, x := range nums {
		m = max(m, x)
	}
	n := len(nums)
	uf := newUnionFind(m + n + 1)
	for i, x := range nums {
		for _, j := range p[x] {
			uf.union(i, j+n)
		}
	}
	s := map[int]bool{}
	for i := 0; i < n; i++ {
		s[uf.find(i)] = true
	}
	return len(s) == 1
}