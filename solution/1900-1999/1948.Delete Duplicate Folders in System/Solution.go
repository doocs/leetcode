type Trie struct {
	children map[string]*Trie
	deleted  bool
}

func NewTrie() *Trie {
	return &Trie{
		children: make(map[string]*Trie),
	}
}

func deleteDuplicateFolder(paths [][]string) (ans [][]string) {
	root := NewTrie()
	for _, path := range paths {
		cur := root
		for _, name := range path {
			if _, exists := cur.children[name]; !exists {
				cur.children[name] = NewTrie()
			}
			cur = cur.children[name]
		}
	}

	g := make(map[string]*Trie)

	var dfs func(*Trie) string
	dfs = func(node *Trie) string {
		if len(node.children) == 0 {
			return ""
		}
		var subs []string
		for name, child := range node.children {
			subs = append(subs, name+"("+dfs(child)+")")
		}
		sort.Strings(subs)
		s := strings.Join(subs, "")
		if existingNode, exists := g[s]; exists {
			node.deleted = true
			existingNode.deleted = true
		} else {
			g[s] = node
		}
		return s
	}

	var dfs2 func(*Trie, []string)
	dfs2 = func(node *Trie, path []string) {
		if node.deleted {
			return
		}
		if len(path) > 0 {
			ans = append(ans, append([]string{}, path...))
		}
		for name, child := range node.children {
			dfs2(child, append(path, name))
		}
	}

	dfs(root)
	dfs2(root, []string{})
	return ans
}
