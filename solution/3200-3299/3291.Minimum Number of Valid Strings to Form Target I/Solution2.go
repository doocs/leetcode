type Trie struct {
	children [26]*Trie
}

func (t *Trie) insert(word string) {
	node := t
	for _, c := range word {
		idx := c - 'a'
		if node.children[idx] == nil {
			node.children[idx] = &Trie{}
		}
		node = node.children[idx]
	}
}

func minValidStrings(words []string, target string) int {
	n := len(target)
	trie := &Trie{}
	for _, w := range words {
		trie.insert(w)
	}
	const inf int = 1 << 30
	f := make([]int, n+1)
	for i := 0; i < n; i++ {
		f[i] = inf
	}
	for i := n - 1; i >= 0; i-- {
		node := trie
		for j := i; j < n; j++ {
			k := int(target[j] - 'a')
			if node.children[k] == nil {
				break
			}
			node = node.children[k]
			f[i] = min(f[i], 1+f[j+1])
		}
	}
	if f[0] < inf {
		return f[0]
	}
	return -1
}
