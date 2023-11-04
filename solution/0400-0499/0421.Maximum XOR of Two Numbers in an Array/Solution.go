type Trie struct {
	children [2]*Trie
}

func newTrie() *Trie {
	return &Trie{}
}

func (t *Trie) insert(x int) {
	node := t
	for i := 30; i >= 0; i-- {
		v := x >> i & 1
		if node.children[v] == nil {
			node.children[v] = newTrie()
		}
		node = node.children[v]
	}
}

func (t *Trie) search(x int) int {
	node := t
	ans := 0
	for i := 30; i >= 0; i-- {
		v := x >> i & 1
		if node.children[v^1] != nil {
			ans |= 1 << i
			node = node.children[v^1]
		} else {
			node = node.children[v]
		}
	}
	return ans
}

func findMaximumXOR(nums []int) (ans int) {
	trie := newTrie()
	for _, x := range nums {
		trie.insert(x)
		ans = max(ans, trie.search(x))
	}
	return ans
}