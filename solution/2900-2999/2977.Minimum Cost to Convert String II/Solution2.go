type Node struct {
	children [26]*Node
	v        int
}

func newNode() *Node {
	return &Node{v: -1}
}

func minimumCost(source string, target string, original []string, changed []string, cost []int) int64 {
	inf := 1 << 60
	root := newNode()
	idx := 0
	m := len(cost)
	g := make([][]int, m<<1)
	for i := range g {
		g[i] = make([]int, m<<1)
		for j := range g[i] {
			g[i][j] = inf
		}
		g[i][i] = 0
	}
	insert := func(w string) int {
		node := root
		for _, c := range w {
			i := c - 'a'
			if node.children[i] == nil {
				node.children[i] = newNode()
			}
			node = node.children[i]
		}
		if node.v < 0 {
			node.v = idx
			idx++
		}
		return node.v
	}
	for i := range original {
		x := insert(original[i])
		y := insert(changed[i])
		g[x][y] = min(g[x][y], cost[i])
	}
	for k := 0; k < idx; k++ {
		for i := 0; i < idx; i++ {
			if g[i][k] >= inf {
				continue
			}
			for j := 0; j < idx; j++ {
				g[i][j] = min(g[i][j], g[i][k]+g[k][j])
			}
		}
	}
	n := len(source)
	f := make([]int, n+1)
	for i := 0; i < n; i++ {
		f[i] = inf
	}
	for i := n - 1; i >= 0; i-- {
		if source[i] == target[i] {
			f[i] = f[i+1]
		}
		p, q := root, root
		for j := i; j < n; j++ {
			p = p.children[source[j]-'a']
			q = q.children[target[j]-'a']
			if p == nil || q == nil {
				break
			}
			if p.v < 0 || q.v < 0 {
				continue
			}
			f[i] = min(f[i], f[j+1]+g[p.v][q.v])
		}
	}
	if f[0] >= inf {
		return -1
	}
	return int64(f[0])
}
