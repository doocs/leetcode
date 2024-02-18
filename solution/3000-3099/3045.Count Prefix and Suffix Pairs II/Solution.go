type Node struct {
	children map[int]*Node
	cnt      int
}

func countPrefixSuffixPairs(words []string) (ans int64) {
	trie := &Node{children: make(map[int]*Node)}
	for _, s := range words {
		node := trie
		m := len(s)
		for i := 0; i < m; i++ {
			p := int(s[i])*32 + int(s[m-i-1])
			if _, ok := node.children[p]; !ok {
				node.children[p] = &Node{children: make(map[int]*Node)}
			}
			node = node.children[p]
			ans += int64(node.cnt)
		}
		node.cnt++
	}
	return
}