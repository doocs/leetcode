type trieNode struct {
	count    int
	depth    int
	children [26]int
}

func longestCommonPrefix(words []string, k int) []int {
	n := len(words)
	ans := make([]int, n)
	if n-1 < k {
		return ans
	}

	trie := []trieNode{{}}
	for i := range trie[0].children {
		trie[0].children[i] = -1
	}
	for _, word := range words {
		cur := 0
		for _, c := range word {
			idx := int(c - 'a')
			if trie[cur].children[idx] == -1 {
				trie[cur].children[idx] = len(trie)
				node := trieNode{depth: trie[cur].depth + 1}
				for j := range node.children {
					node.children[j] = -1
				}
				trie = append(trie, node)
			}
			cur = trie[cur].children[idx]
			trie[cur].count++
		}
	}

	maxDepth := 0
	for i := 1; i < len(trie); i++ {
		if trie[i].count >= k {
			maxDepth = max(maxDepth, trie[i].depth)
		}
	}

	globalCount := make([]int, maxDepth+1)
	for i := 1; i < len(trie); i++ {
		if trie[i].count >= k && trie[i].depth <= maxDepth {
			globalCount[trie[i].depth]++
		}
	}

	fragileList := make([][]int, n)
	for i, word := range words {
		cur := 0
		for _, c := range word {
			idx := int(c - 'a')
			cur = trie[cur].children[idx]
			if trie[cur].count == k {
				fragileList[i] = append(fragileList[i], trie[cur].depth)
			}
		}
	}

	segSize := maxDepth
	if segSize < 1 {
		return ans
	}

	tree := make([]int, 4*(segSize+1))
	for i := range tree {
		tree[i] = -1
	}

	var build func(idx, l, r int)
	build = func(idx, l, r int) {
		if l == r {
			if globalCount[l] > 0 {
				tree[idx] = l
			} else {
				tree[idx] = -1
			}
			return
		}
		mid := (l + r) / 2
		build(idx*2, l, mid)
		build(idx*2+1, mid+1, r)
		tree[idx] = max(tree[idx*2], tree[idx*2+1])
	}

	var update func(idx, l, r, pos, newVal int)
	update = func(idx, l, r, pos, newVal int) {
		if l == r {
			if newVal > 0 {
				tree[idx] = l
			} else {
				tree[idx] = -1
			}
			return
		}
		mid := (l + r) / 2
		if pos <= mid {
			update(idx*2, l, mid, pos, newVal)
		} else {
			update(idx*2+1, mid+1, r, pos, newVal)
		}
		tree[idx] = max(tree[idx*2], tree[idx*2+1])
	}

	build(1, 1, segSize)
	for i := 0; i < n; i++ {
		for _, d := range fragileList[i] {
			update(1, 1, segSize, d, globalCount[d]-1)
		}
		res := tree[1]
		if res == -1 {
			ans[i] = 0
		} else {
			ans[i] = res
		}
		for _, d := range fragileList[i] {
			update(1, 1, segSize, d, globalCount[d])
		}
	}
	return ans
}
