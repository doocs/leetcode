type Trie struct {
	children [26]*Trie
	v        string
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
	node.v = word
}

func (this *Trie) search(word string) string {
	node := this
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			break
		}
		node = node.children[c]
		if node.v != "" {
			return node.v
		}
	}
	return word
}

func replaceWords(dictionary []string, sentence string) string {
	trie := newTrie()
	for _, v := range dictionary {
		trie.insert(v)
	}
	var ans []string
	for _, v := range strings.Split(sentence, " ") {
		ans = append(ans, trie.search(v))
	}
	return strings.Join(ans, " ")
}