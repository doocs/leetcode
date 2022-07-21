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

func findAllConcatenatedWordsInADict(words []string) (ans []string) {
	sort.Slice(words, func(i, j int) bool { return len(words[i]) < len(words[j]) })
	trie := newTrie()
	var dfs func(string) bool
	dfs = func(w string) bool {
		if w == "" {
			return true
		}
		node := trie
		for i, c := range w {
			c -= 'a'
			if node.children[c] == nil {
				return false
			}
			node = node.children[c]
			if node.isEnd && dfs(w[i+1:]) {
				return true
			}
		}
		return false
	}
	for _, w := range words {
		if dfs(w) {
			ans = append(ans, w)
		} else {
			trie.insert(w)
		}
	}
	return
}