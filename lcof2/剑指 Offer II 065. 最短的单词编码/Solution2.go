type Trie struct {
	children [26]*Trie
}

func newTrie() *Trie {
	return &Trie{}
}

func (this *Trie) insert(w string) int {
	node := this
	pref := true
	for i := len(w) - 1; i >= 0; i-- {
		idx := w[i] - 'a'
		if node.children[idx] == nil {
			pref = false
			node.children[idx] = newTrie()
		}
		node = node.children[idx]
	}
	if pref {
		return 0
	}
	return len(w) + 1
}

func minimumLengthEncoding(words []string) int {
	sort.Slice(words, func(i, j int) bool { return len(words[i]) > len(words[j]) })
	trie := newTrie()
	ans := 0
	for _, w := range words {
		ans += trie.insert(w)
	}
	return ans
}