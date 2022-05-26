type Trie struct {
	children [26]*Trie
	idx      int
}

func newTrie() *Trie {
	return &Trie{idx: -1}
}

func (this *Trie) insert(word string, idx int) {
	node := this
	for _, c := range word {
		idx := c - 'a'
		if node.children[idx] == nil {
			node.children[idx] = newTrie()
		}
		node = node.children[idx]
	}
	node.idx = idx
}

func (this *Trie) search(word string) []int {
	node := this
	var res []int
	for _, c := range word {
		idx := c - 'a'
		if node.children[idx] == nil {
			return res
		}
		node = node.children[idx]
		if node.idx != -1 {
			res = append(res, node.idx)
		}
	}
	return res
}

func multiSearch(big string, smalls []string) [][]int {
	tree := newTrie()
	for i, s := range smalls {
		tree.insert(s, i)
	}
	n := len(smalls)
	ans := make([][]int, n)
	for i := range big {
		s := big[i:]
		t := tree.search(s)
		for _, idx := range t {
			ans[idx] = append(ans[idx], i)
		}
	}
	return ans
}