type Trie struct {
	children [26]*Trie
	isEnd    bool
}

func newTrie() *Trie {
	return &Trie{}
}
func (this *Trie) insert(word string) {
	node := this
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
	}
	node.isEnd = true
}

func (this *Trie) search(word string) bool {
	node := this
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			return false
		}
		node = node.children[c]
	}
	return node.isEnd
}

func longestWord(words []string) string {
	sort.Slice(words, func(i, j int) bool {
		a, b := words[i], words[j]
		if len(a) != len(b) {
			return len(a) < len(b)
		}
		return a > b
	})
	trie := newTrie()
	var dfs func(string) bool
	dfs = func(w string) bool {
		if len(w) == 0 {
			return true
		}
		for i := 1; i <= len(w); i++ {
			if trie.search(w[:i]) && dfs(w[i:]) {
				return true
			}
		}
		return false
	}
	ans := ""
	for _, w := range words {
		if dfs(w) {
			ans = w
		}
		trie.insert(w)
	}
	return ans
}