type Trie struct {
	children [2]*Trie
}

func newTrie() *Trie {
	return &Trie{}
}

func (this *Trie) insert(x int) {
	node := this
	for i := 47; i >= 0; i-- {
		v := (x >> i) & 1
		if node.children[v] == nil {
			node.children[v] = newTrie()
		}
		node = node.children[v]
	}
}

func (this *Trie) search(x int) int {
	node := this
	res := 0
	for i := 47; i >= 0; i-- {
		v := (x >> i) & 1
		if node == nil {
			return res
		}
		if node.children[v^1] != nil {
			res = res<<1 | 1
			node = node.children[v^1]
		} else {
			res <<= 1
			node = node.children[v]
		}
	}
	return res
}

func maxXor(n int, edges [][]int, values []int) int64 {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	s := make([]int, n)
	stk := [][3]int{{0, -1, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, fa, state := cur[0], cur[1], cur[2]
		if state == 0 {
			stk = append(stk, [3]int{i, fa, 1})
			for k := len(g[i]) - 1; k >= 0; k-- {
				j := g[i][k]
				if j != fa {
					stk = append(stk, [3]int{j, i, 0})
				}
			}
		} else {
			t := values[i]
			for _, j := range g[i] {
				if j != fa {
					t += s[j]
				}
			}
			s[i] = t
		}
	}
	ans := 0
	tree := newTrie()
	stk = [][3]int{{0, -1, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, fa, state := cur[0], cur[1], cur[2]
		if state == 0 {
			ans = max(ans, tree.search(s[i]))
			stk = append(stk, [3]int{i, fa, 1})
			for k := len(g[i]) - 1; k >= 0; k-- {
				j := g[i][k]
				if j != fa {
					stk = append(stk, [3]int{j, i, 0})
				}
			}
		} else {
			tree.insert(s[i])
		}
	}
	return int64(ans)
}
