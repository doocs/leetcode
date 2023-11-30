type Trie struct {
	children [26]*Trie
	isEnd    bool
}

func newTrie() *Trie {
	return &Trie{}
}

func (t *Trie) insert(w string) {
	node := t
	for _, c := range w {
		idx := c - 'a'
		if node.children[idx] == nil {
			node.children[idx] = newTrie()
		}
		node = node.children[idx]
	}
	node.isEnd = true
}

func (t *Trie) search(w string) bool {
	node := t
	for _, c := range w {
		idx := c - 'a'
		node = node.children[idx]
		if !node.isEnd {
			return false
		}
	}
	return true
}

func longestWord(words []string) string {
	trie := newTrie()
	for _, w := range words {
		trie.insert(w)
	}
	ans := ""
	for _, w := range words {
		if (len(w) > len(ans) || (len(w) == len(ans) && w < ans)) && trie.search(w) {
			ans = w
		}
	}
	return ans
}