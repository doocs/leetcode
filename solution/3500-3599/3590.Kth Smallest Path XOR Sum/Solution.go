type binarySumTrie struct {
	count    int
	children [2]*binarySumTrie
}

func (t *binarySumTrie) add(num, delta, bit int) {
	t.count += delta
	if bit < 0 {
		return
	}
	b := (num >> bit) & 1
	if t.children[b] == nil {
		t.children[b] = &binarySumTrie{}
	}
	t.children[b].add(num, delta, bit-1)
}

func (t *binarySumTrie) collect(prefix, bit int, output *[]int) {
	if t.count == 0 {
		return
	}
	if bit < 0 {
		*output = append(*output, prefix)
		return
	}
	if t.children[0] != nil {
		t.children[0].collect(prefix, bit-1, output)
	}
	if t.children[1] != nil {
		t.children[1].collect(prefix|(1<<bit), bit-1, output)
	}
}

func (t *binarySumTrie) exists(num, bit int) bool {
	if t.count == 0 {
		return false
	}
	if bit < 0 {
		return true
	}
	b := (num >> bit) & 1
	return t.children[b] != nil && t.children[b].exists(num, bit-1)
}

func (t *binarySumTrie) findKth(k, bit int) int {
	if k > t.count {
		return -1
	}
	if bit < 0 {
		return 0
	}
	leftCount := 0
	if t.children[0] != nil {
		leftCount = t.children[0].count
	}
	if k <= leftCount {
		return t.children[0].findKth(k, bit-1)
	}
	if t.children[1] != nil {
		return (1 << bit) + t.children[1].findKth(k-leftCount, bit-1)
	}
	return -1
}

func kthSmallest(par []int, vals []int, queries [][]int) []int {
	n := len(par)
	tree := make([][]int, n)
	for i := 1; i < n; i++ {
		tree[par[i]] = append(tree[par[i]], i)
	}
	pathXor := append([]int(nil), vals...)
	var computeXor func(int, int)
	computeXor = func(node, acc int) {
		pathXor[node] ^= acc
		for _, child := range tree[node] {
			computeXor(child, pathXor[node])
		}
	}
	computeXor(0, 0)

	nodeQueries := make([][][2]int, n)
	for i, q := range queries {
		nodeQueries[q[0]] = append(nodeQueries[q[0]], [2]int{q[1], i})
	}

	pool := make([]*binarySumTrie, n)
	result := make([]int, len(queries))
	var dfs func(int)
	dfs = func(node int) {
		pool[node] = &binarySumTrie{}
		pool[node].add(pathXor[node], 1, 17)
		for _, child := range tree[node] {
			dfs(child)
			if pool[node].count < pool[child].count {
				pool[node], pool[child] = pool[child], pool[node]
			}
			vals := []int{}
			pool[child].collect(0, 17, &vals)
			for _, val := range vals {
				if !pool[node].exists(val, 17) {
					pool[node].add(val, 1, 17)
				}
			}
		}
		for _, q := range nodeQueries[node] {
			if pool[node].count < q[0] {
				result[q[1]] = -1
			} else {
				result[q[1]] = pool[node].findKth(q[0], 17)
			}
		}
	}
	dfs(0)
	return result
}
