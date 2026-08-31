func getResults(queries [][]int) []bool {
	m := 0
	for _, q := range queries {
		m = max(m, q[1])
	}
	st := redblacktree.New[int, struct{}]()
	st.Put(0, struct{}{})
	st.Put(m+1, struct{}{})
	for _, q := range queries {
		if q[0] == 1 {
			st.Put(q[1], struct{}{})
		}
	}
	tree := newBinaryIndexedTree(m + 1)
	it := st.Iterator()
	it.Next()
	pre := it.Key()
	for it.Next() {
		x := it.Key()
		tree.update(x, x-pre)
		pre = x
	}
	ans := []bool{}
	for i := len(queries) - 1; i >= 0; i-- {
		q := queries[i]
		x := q[1]
		if q[0] == 1 {
			nxt, _ := st.Ceiling(x + 1)
			p, _ := st.Floor(x - 1)
			st.Remove(x)
			tree.update(nxt.Key, nxt.Key-p.Key)
		} else {
			node, _ := st.Floor(x)
			p := node.Key
			ans = append(ans, tree.query(p) >= q[2] || x-p >= q[2])
		}
	}
	slices.Reverse(ans)
	return ans
}

type binaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *binaryIndexedTree {
	return &binaryIndexedTree{n: n, c: make([]int, n+1)}
}

func (t *binaryIndexedTree) update(x, v int) {
	for x <= t.n {
		t.c[x] = max(t.c[x], v)
		x += x & -x
	}
}

func (t *binaryIndexedTree) query(x int) int {
	mx := 0
	for x > 0 {
		mx = max(mx, t.c[x])
		x -= x & -x
	}
	return mx
}
