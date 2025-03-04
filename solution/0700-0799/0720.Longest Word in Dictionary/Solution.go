type Trie struct {
	children [26]*Trie
	isEnd    bool
}

func (t *Trie) insert(w string) {
	node := t
	for i := 0; i < len(w); i++ {
		idx := w[i] - 'a'
		if node.children[idx] == nil {
			node.children[idx] = &Trie{}
		}
		node = node.children[idx]
	}
	node.isEnd = true
}

func (t *Trie) search(w string) bool {
	node := t
	for i := 0; i < len(w); i++ {
		idx := w[i] - 'a'
		if node.children[idx] == nil || !node.children[idx].isEnd {
			return false
		}
		node = node.children[idx]
	}
	return true
}

func longestWord(words []string) string {
	trie := &Trie{}
	for _, w := range words {
		trie.insert(w)
	}

	ans := ""
	for _, w := range words {
		if trie.search(w) && (len(ans) < len(w) || (len(ans) == len(w) && w < ans)) {
			ans = w
		}
	}
	return ans
}
