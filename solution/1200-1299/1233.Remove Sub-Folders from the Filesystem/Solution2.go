type Trie struct {
	children map[string]*Trie
	fid      int
}

func newTrie() *Trie {
	return &Trie{map[string]*Trie{}, -1}
}

func (this *Trie) insert(fid int, f string) {
	node := this
	ps := strings.Split(f, "/")
	for _, p := range ps[1:] {
		if _, ok := node.children[p]; !ok {
			node.children[p] = newTrie()
		}
		node = node.children[p]
	}
	node.fid = fid
}

func (this *Trie) search() (ans []int) {
	var dfs func(*Trie)
	dfs = func(root *Trie) {
		if root.fid != -1 {
			ans = append(ans, root.fid)
			return
		}
		for _, child := range root.children {
			dfs(child)
		}
	}
	dfs(this)
	return
}

func removeSubfolders(folder []string) (ans []string) {
	trie := newTrie()
	for i, f := range folder {
		trie.insert(i, f)
	}
	for _, i := range trie.search() {
		ans = append(ans, folder[i])
	}
	return
}