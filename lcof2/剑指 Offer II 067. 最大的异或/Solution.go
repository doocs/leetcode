type Trie struct {
	children [26]*Trie
}

func newTrie() *Trie {
	return &Trie{}
}

func (this *Trie) insert(x int) {
	node := this
	for i := 30; i >= 0; i-- {
		v := (x >> i) & 1
		if node.children[v] == nil {
			node.children[v] = newTrie()
		}
		node = node.children[v]
	}
}

func (this *Trie) search(x int) int {
	node := this
	res := 0
	for i := 30; i >= 0; i-- {
		v := (x >> i) & 1
		if node.children[v^1] != nil {
			res = res<<1 | 1
			node = node.children[v^1]
		} else {
			res <<= 1
			node = node.children[v]
		}
	}
	return res
}

func findMaximumXOR(nums []int) int {
	trie := newTrie()
	ans := 0
	for _, v := range nums {
		trie.insert(v)
		ans = max(ans, trie.search(v))
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}