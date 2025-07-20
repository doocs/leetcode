type TrieNode struct {
    children map[string]*TrieNode
    key      string
    deleted  bool
}

func deleteDuplicateFolder(paths [][]string) [][]string {
    root := &TrieNode{children: make(map[string]*TrieNode)}

    for _, path := range paths {
        current := root
        for _, folder := range path {
            if _, ok := current.children[folder]; !ok {
                current.children[folder] = &TrieNode{
                    children: make(map[string]*TrieNode),
                    key:      folder,
                }
            }
            current = current.children[folder]
        }
    }

    seen := make(map[string]*TrieNode)
    var dfs func(*TrieNode) string
    dfs = func(node *TrieNode) string {
        if node == nil || len(node.children) == 0 {
            return ""
        }

        var keys []string
        for key, child := range node.children {
            serialized := dfs(child)
            keys = append(keys, key+"("+serialized+")")
        }
        sort.Strings(keys)
        serialized := strings.Join(keys, "")

        if existing, ok := seen[serialized]; ok {
            existing.deleted = true
            node.deleted = true
        } else {
            seen[serialized] = node
        }

        return serialized
    }
    dfs(root)

    var result [][]string
    var path []string
    var collect func(*TrieNode)
    collect = func(node *TrieNode) {
        if node.deleted {
            return
        }
        if len(path) > 0 {
            newPath := make([]string, len(path))
            copy(newPath, path)
            result = append(result, newPath)
        }
        for key, child := range node.children {
            path = append(path, key)
            collect(child)
            path = path[:len(path)-1]
        }
    }
    collect(root)

    return result
}
