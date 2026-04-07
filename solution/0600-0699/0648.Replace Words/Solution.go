type Trie struct {
	children [26]*Trie
	isEnd    bool
}

func (t *Trie) insert(w string) {
	node := t
	for _, c := range w {
		idx := c - 'a'
		if node.children[idx] == nil {
			node.children[idx] = &Trie{}
		}
		node = node.children[idx]
	}
	node.isEnd = true
}

func (t *Trie) search(w string) string {
	node := t
	for i, c := range w {
		idx := c - 'a'
		if node.children[idx] == nil {
			return w
		}
		node = node.children[idx]
		if node.isEnd {
			return w[:i+1]
		}
	}
	return w
}

func replaceWords(dictionary []string, sentence string) string {
	trie := &Trie{}
	for _, w := range dictionary {
		trie.insert(w)
	}

	words := strings.Split(sentence, " ")
	for i, w := range words {
		words[i] = trie.search(w)
	}
	return strings.Join(words, " ")
}
