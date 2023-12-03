type Trie struct {
	children [26]*Trie
	isEnd    bool
}

func NewTrie() *Trie {
	return &Trie{}
}

func (t *Trie) Insert(w string) {
	node := t
	for _, c := range w {
		i := c - 'a'
		if node.children[i] == nil {
			node.children[i] = NewTrie()
		}
		node = node.children[i]
	}
	node.isEnd = true
}

func (t *Trie) Search(w string) bool {
	var dfs func(int, *Trie, int) bool
	dfs = func(i int, node *Trie, diff int) bool {
		if i >= len(w) {
			return diff == 1 && node.isEnd
		}
		j := int(w[i] - 'a')
		if node.children[j] != nil && dfs(i+1, node.children[j], diff) {
			return true
		}
		if diff == 0 {
			for k := 0; k < 26; k++ {
				if k != j && node.children[k] != nil && dfs(i+1, node.children[k], 1) {
					return true
				}
			}
		}
		return false
	}
	return dfs(0, t, 0)
}

type MagicDictionary struct {
	trie *Trie
}

func Constructor() MagicDictionary {
	return MagicDictionary{trie: NewTrie()}
}

func (md *MagicDictionary) BuildDict(dictionary []string) {
	for _, w := range dictionary {
		md.trie.Insert(w)
	}
}

func (md *MagicDictionary) Search(searchWord string) bool {
	return md.trie.Search(searchWord)
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * obj := Constructor();
 * obj.BuildDict(dictionary);
 * param_2 := obj.Search(searchWord);
 */