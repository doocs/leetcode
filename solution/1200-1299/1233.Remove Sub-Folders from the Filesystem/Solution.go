type Trie struct {
	children map[string]*Trie
	isEnd    bool
}

func newTrie() *Trie {
	m := map[string]*Trie{}
	return &Trie{children: m}
}

func (this *Trie) insert(w string) {
	node := this
	for _, p := range strings.Split(w, "/")[1:] {
		if _, ok := node.children[p]; !ok {
			node.children[p] = newTrie()
		}
		node, _ = node.children[p]
	}
	node.isEnd = true
}

func (this *Trie) search(w string) bool {
	node := this
	for _, p := range strings.Split(w, "/")[1:] {
		if _, ok := node.children[p]; !ok {
			return false
		}
		node, _ = node.children[p]
		if node.isEnd {
			return true
		}
	}
	return false
}

func removeSubfolders(folder []string) []string {
	sort.Slice(folder, func(i, j int) bool {
		return len(strings.Split(folder[i], "/")) < len(strings.Split(folder[j], "/"))
	})
	trie := newTrie()
	var ans []string
	for _, v := range folder {
		if !trie.search(v) {
			trie.insert(v)
			ans = append(ans, v)
		}
	}
	return ans
}