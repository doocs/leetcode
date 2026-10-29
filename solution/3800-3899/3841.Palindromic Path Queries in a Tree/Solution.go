type BinaryIndexedTree struct {
	n    int
	tree []int
}

func NewBinaryIndexedTree(n int) *BinaryIndexedTree {
	return &BinaryIndexedTree{n: n, tree: make([]int, n+1)}
}

func (b *BinaryIndexedTree) update(i, delta int) {
	for i++; i <= b.n; i += i & -i {
		b.tree[i] ^= delta
	}
}

func (b *BinaryIndexedTree) query(i int) int {
	res := 0
	for ; i > 0; i -= i & -i {
		res ^= b.tree[i]
	}
	return res
}

func palindromePath(n int, edges [][]int, s string, queries []string) []bool {
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}

	parent := make([]int, n)
	depth := make([]int, n)
	tin := make([]int, n)
	tout := make([]int, n)
	timer := 0

	var dfs func(int, int)
	dfs = func(u, p int) {
		if p == -1 {
			parent[u] = 0
		} else {
			parent[u] = p
		}
		tin[u] = timer
		timer++
		for _, v := range g[u] {
			if v != p {
				depth[v] = depth[u] + 1
				dfs(v, u)
			}
		}
		tout[u] = timer
	}
	dfs(0, -1)

	m := 1
	for (1 << m) <= n {
		m++
	}
	up := make([][]int, m)
	up[0] = append([]int(nil), parent...)
	for j := 1; j < m; j++ {
		up[j] = make([]int, n)
		for i := 0; i < n; i++ {
			up[j][i] = up[j-1][up[j-1][i]]
		}
	}

	bit := make([]int, n)
	for i := 0; i < n; i++ {
		bit[i] = 1 << (s[i] - 'a')
	}

	tree := NewBinaryIndexedTree(n)
	for i := 0; i < n; i++ {
		tree.update(tin[i], bit[i])
		tree.update(tout[i], bit[i])
	}

	lca := func(u, v int) int {
		if depth[u] < depth[v] {
			u, v = v, u
		}
		d := depth[u] - depth[v]
		for j := 0; d > 0; j++ {
			if d&1 != 0 {
				u = up[j][u]
			}
			d >>= 1
		}
		if u == v {
			return u
		}
		for j := m - 1; j >= 0; j-- {
			if up[j][u] != up[j][v] {
				u = up[j][u]
				v = up[j][v]
			}
		}
		return parent[u]
	}

	ans := make([]bool, 0)
	for _, q := range queries {
		var op string
		var u int
		fmt.Sscanf(q, "%s %d", &op, &u)
		if op == "update" {
			var c string
			fmt.Sscanf(q, "%s %d %s", &op, &u, &c)
			y := 1 << (c[0] - 'a')
			if bit[u] != y {
				delta := bit[u] ^ y
				tree.update(tin[u], delta)
				tree.update(tout[u], delta)
				bit[u] = y
			}
		} else {
			var v int
			fmt.Sscanf(q, "%s %d %d", &op, &u, &v)
			w := lca(u, v)
			mask := tree.query(tin[u]+1) ^ tree.query(tin[v]+1) ^ bit[w]
			ans = append(ans, mask&(mask-1) == 0)
		}
	}
	return ans
}
