type Trie struct {
	children map[string]*Trie
	v        int
}

func newTrie() *Trie {
	m := map[string]*Trie{}
	return &Trie{children: m}
}

func (this *Trie) insert(w string, v int) bool {
	node := this
	ps := strings.Split(w, "/")
	for _, p := range ps[1 : len(ps)-1] {
		if _, ok := node.children[p]; !ok {
			return false
		}
		node, _ = node.children[p]
	}
	x := ps[len(ps)-1]
	if _, ok := node.children[x]; ok {
		return false
	}
	node.children[x] = newTrie()
	node, _ = node.children[x]
	node.v = v
	return true
}

func (this *Trie) search(w string) int {
	node := this
	for _, p := range strings.Split(w, "/")[1:] {
		if _, ok := node.children[p]; !ok {
			return -1
		}
		node, _ = node.children[p]
	}
	if node.v == 0 {
		return -1
	}
	return node.v
}

type FileSystem struct {
	trie *Trie
}

func Constructor() FileSystem {
	return FileSystem{newTrie()}
}

func (this *FileSystem) CreatePath(path string, value int) bool {
	return this.trie.insert(path, value)
}

func (this *FileSystem) Get(path string) int {
	return this.trie.search(path)
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.CreatePath(path,value);
 * param_2 := obj.Get(path);
 */