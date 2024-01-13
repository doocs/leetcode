type Trie struct {
	children [26]*Trie
	cnt      int
}

func newTrie() *Trie {
	return &Trie{}
}

func (this *Trie) insert(w string) {
	node := this
	for _, c := range w {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
		node.cnt++
	}
}

func (this *Trie) search(pref string) int {
	node := this
	for _, c := range pref {
		c -= 'a'
		if node.children[c] == nil {
			return 0
		}
		node = node.children[c]
	}
	return node.cnt
}

func prefixCount(words []string, pref string) int {
	tree := newTrie()
	for _, w := range words {
		tree.insert(w)
	}
	return tree.search(pref)
}