type trie struct {
	children map[string]*trie
	v        int
}

func newTrie(v int) *trie {
	return &trie{map[string]*trie{}, v}
}

func (t *trie) insert(w string, v int) bool {
	node := t
	ps := strings.Split(w, "/")
	for _, p := range ps[1 : len(ps)-1] {
		if _, ok := node.children[p]; !ok {
			return false
		}
		node = node.children[p]
	}
	if _, ok := node.children[ps[len(ps)-1]]; ok {
		return false
	}
	node.children[ps[len(ps)-1]] = newTrie(v)
	return true
}

func (t *trie) search(w string) int {
	node := t
	ps := strings.Split(w, "/")
	for _, p := range ps[1:] {
		if _, ok := node.children[p]; !ok {
			return -1
		}
		node = node.children[p]
	}
	return node.v
}

type FileSystem struct {
	trie *trie
}

func Constructor() FileSystem {
	return FileSystem{trie: newTrie(-1)}
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