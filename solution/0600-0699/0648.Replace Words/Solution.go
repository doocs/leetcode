type Trie struct {
	children [26]*Trie
	ref      int
}

func newTrie() *Trie {
	return &Trie{ref: -1}
}

func (this *Trie) insert(w string, i int) {
	node := this
	for _, c := range w {
		idx := c - 'a'
		if node.children[idx] == nil {
			node.children[idx] = newTrie()
		}
		node = node.children[idx]
	}
	node.ref = i
}

func (this *Trie) search(w string) int {
	node := this
	for _, c := range w {
		idx := c - 'a'
		if node.children[idx] == nil {
			return -1
		}
		node = node.children[idx]
		if node.ref != -1 {
			return node.ref
		}
	}
	return -1
}

func replaceWords(dictionary []string, sentence string) string {
	trie := newTrie()
	for i, w := range dictionary {
		trie.insert(w, i)
	}
	ans := strings.Builder{}
	for _, w := range strings.Split(sentence, " ") {
		if idx := trie.search(w); idx != -1 {
			ans.WriteString(dictionary[idx])
		} else {
			ans.WriteString(w)
		}
		ans.WriteByte(' ')
	}
	return ans.String()[:ans.Len()-1]
}