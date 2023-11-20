const inf int = 1 << 30

type BinaryIndexedTree struct {
	n int
	c []int
}

func NewBinaryIndexedTree(n int) BinaryIndexedTree {
	c := make([]int, n+1)
	for i := range c {
		c[i] = inf
	}
	return BinaryIndexedTree{n: n, c: c}
}

func (bit *BinaryIndexedTree) update(x, v int) {
	for x <= bit.n {
		bit.c[x] = min(bit.c[x], v)
		x += x & -x
	}
}

func (bit *BinaryIndexedTree) query(x int) int {
	mi := inf
	for x > 0 {
		mi = min(mi, bit.c[x])
		x -= x & -x
	}
	if mi == inf {
		return -1
	}
	return mi
}

func leftmostBuildingQueries(heights []int, queries [][]int) []int {
	n, m := len(heights), len(queries)
	for _, q := range queries {
		if q[0] > q[1] {
			q[0], q[1] = q[1], q[0]
		}
	}
	idx := make([]int, m)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return queries[idx[j]][1] < queries[idx[i]][1] })
	s := make([]int, n)
	copy(s, heights)
	sort.Ints(s)
	ans := make([]int, m)
	tree := NewBinaryIndexedTree(n)
	j := n - 1
	for _, i := range idx {
		l, r := queries[i][0], queries[i][1]
		for ; j > r; j-- {
			k := n - sort.SearchInts(s, heights[j]) + 1
			tree.update(k, j)
		}
		if l == r || heights[l] < heights[r] {
			ans[i] = r
		} else {
			k := n - sort.SearchInts(s, heights[l])
			ans[i] = tree.query(k)
		}
	}
	return ans
}