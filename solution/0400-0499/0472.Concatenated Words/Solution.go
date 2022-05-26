type trie struct {
	children [26]*trie
	isEnd    bool
}

func (root *trie) insert(word string) {
	node := root
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = &trie{}
		}
		node = node.children[c]
	}
	node.isEnd = true
}

func (root *trie) dfs(word string) bool {
	if word == "" {
		return true
	}
	node := root
	for i, c := range word {
		node = node.children[c-'a']
		if node == nil {
			return false
		}
		if node.isEnd && root.dfs(word[i+1:]) {
			return true
		}
	}
	return false
}

func findAllConcatenatedWordsInADict(words []string) (ans []string) {
	sort.Slice(words, func(i, j int) bool { return len(words[i]) < len(words[j]) })
	root := &trie{}
	for _, word := range words {
		if word == "" {
			continue
		}
		if root.dfs(word) {
			ans = append(ans, word)
		} else {
			root.insert(word)
		}
	}
	return
}