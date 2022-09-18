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

func (this *Trie) search(word string) int {
	node := this
	ans := 0
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			return ans
		}
		node = node.children[c]
		ans += node.cnt
	}
	return ans
}

func sumPrefixScores(words []string) []int {
	trie := newTrie()
	for _, w := range words {
		trie.insert(w)
	}
	ans := make([]int, len(words))
	for i, w := range words {
		ans[i] = trie.search(w)
	}
	return ans
}