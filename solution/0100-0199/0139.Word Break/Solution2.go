type trie struct {
	children [26]*trie
	isEnd    bool
}

func newTrie() *trie {
	return &trie{}
}

func (t *trie) insert(w string) {
	node := t
	for _, c := range w {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
	}
	node.isEnd = true
}

func wordBreak(s string, wordDict []string) bool {
	trie := newTrie()
	for _, w := range wordDict {
		trie.insert(w)
	}
	n := len(s)
	f := make([]bool, n+1)
	f[n] = true
	for i := n - 1; i >= 0; i-- {
		node := trie
		for j := i; j < n; j++ {
			k := s[j] - 'a'
			if node.children[k] == nil {
				break
			}
			node = node.children[k]
			if node.isEnd && f[j+1] {
				f[i] = true
				break
			}
		}
	}
	return f[0]
}