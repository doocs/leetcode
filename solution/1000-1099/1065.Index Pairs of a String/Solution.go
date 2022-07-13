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
		idx := int(c - 'a')
		if node.children[idx] == nil {
			node.children[idx] = newTrie()
		}
		node = node.children[idx]
	}
	node.isEnd = true
}

func indexPairs(text string, words []string) [][]int {
	trie := newTrie()
	for _, w := range words {
		trie.insert(w)
	}
	n := len(text)
	var ans [][]int
	for i := range text {
		node := trie
		for j := i; j < n; j++ {
			idx := int(text[j] - 'a')
			if node.children[idx] == nil {
				break
			}
			node = node.children[idx]
			if node.isEnd {
				ans = append(ans, []int{i, j})
			}
		}
	}
	return ans
}