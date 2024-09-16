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
	f := make([]int, n)
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] != 0 {
			return f[i]
		}
		node := trie
		f[i] = inf
		for j := i; j < n; j++ {
			k := int(target[j] - 'a')
			if node.children[k] == nil {
				break
			}
			f[i] = min(f[i], 1+dfs(j+1))
			node = node.children[k]
		}
		return f[i]
	}
	if ans := dfs(0); ans < inf {
		return ans
	}
	return -1
}
