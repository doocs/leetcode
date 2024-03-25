const inf = 1 << 30

type Trie struct {
	children [26]*Trie
	length   int
	idx      int
}

func newTrie() *Trie {
	return &Trie{length: inf, idx: inf}
}

func (t *Trie) insert(w string, i int) {
	node := t
	if node.length > len(w) {
		node.length = len(w)
		node.idx = i
	}
	for k := len(w) - 1; k >= 0; k-- {
		idx := int(w[k] - 'a')
		if node.children[idx] == nil {
			node.children[idx] = newTrie()
		}
		node = node.children[idx]
		if node.length > len(w) {
			node.length = len(w)
			node.idx = i
		}
	}
}

func (t *Trie) query(w string) int {
	node := t
	for k := len(w) - 1; k >= 0; k-- {
		idx := int(w[k] - 'a')
		if node.children[idx] == nil {
			break
		}
		node = node.children[idx]
	}
	return node.idx
}

func stringIndices(wordsContainer []string, wordsQuery []string) (ans []int) {
	trie := newTrie()
	for i, w := range wordsContainer {
		trie.insert(w, i)
	}
	for _, w := range wordsQuery {
		ans = append(ans, trie.query(w))
	}
	return
}
