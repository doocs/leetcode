type Trie struct {
	children [26]*Trie
	v        []int
}

func newTrie() *Trie {
	return &Trie{}
}
func (this *Trie) insert(word string, i int) {
	node := this
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
		node.v = append(node.v, i)
	}
}
func (this *Trie) search(word string) []int {
	node := this
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			return []int{}
		}
		node = node.children[c]
	}
	return node.v
}

func wordSquares(words []string) [][]string {
	trie := newTrie()
	for i, w := range words {
		trie.insert(w, i)
	}
	ans := [][]string{}
	var dfs func([]string)
	dfs = func(t []string) {
		if len(t) == len(words[0]) {
			cp := make([]string, len(t))
			copy(cp, t)
			ans = append(ans, cp)
			return
		}
		idx := len(t)
		pref := []byte{}
		for _, v := range t {
			pref = append(pref, v[idx])
		}
		indexes := trie.search(string(pref))
		for _, i := range indexes {
			t = append(t, words[i])
			dfs(t)
			t = t[:len(t)-1]
		}
	}
	for _, w := range words {
		dfs([]string{w})
	}
	return ans
}