type Trie struct {
	name     string
	isFile   bool
	content  strings.Builder
	children map[string]*Trie
}

func newTrie() *Trie {
	m := map[string]*Trie{}
	return &Trie{children: m}
}

func (this *Trie) insert(path string, isFile bool) *Trie {
	node := this
	ps := strings.Split(path, "/")
	for _, p := range ps[1:] {
		if _, ok := node.children[p]; !ok {
			node.children[p] = newTrie()
		}
		node, _ = node.children[p]
	}
	node.isFile = isFile
	if isFile {
		node.name = ps[len(ps)-1]
	}
	return node
}

func (this *Trie) search(path string) *Trie {
	if path == "/" {
		return this
	}
	node := this
	ps := strings.Split(path, "/")
	for _, p := range ps[1:] {
		if _, ok := node.children[p]; !ok {
			return nil
		}
		node, _ = node.children[p]
	}
	return node
}

type FileSystem struct {
	root *Trie
}

func Constructor() FileSystem {
	root := newTrie()
	return FileSystem{root}
}

func (this *FileSystem) Ls(path string) []string {
	var ans []string
	node := this.root.search(path)
	if node == nil {
		return ans
	}
	if node.isFile {
		ans = append(ans, node.name)
		return ans
	}
	for v := range node.children {
		ans = append(ans, v)
	}
	sort.Strings(ans)
	return ans
}

func (this *FileSystem) Mkdir(path string) {
	this.root.insert(path, false)
}

func (this *FileSystem) AddContentToFile(filePath string, content string) {
	node := this.root.insert(filePath, true)
	node.content.WriteString(content)
}

func (this *FileSystem) ReadContentFromFile(filePath string) string {
	node := this.root.search(filePath)
	return node.content.String()
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Ls(path);
 * obj.Mkdir(path);
 * obj.AddContentToFile(filePath,content);
 * param_4 := obj.ReadContentFromFile(filePath);
 */