type Trie struct {
	children [2]*Trie
	cnt      int
}

func newTrie() *Trie {
	return &Trie{}
}

func (t *Trie) insert(x int) {
	node := t
	for i := 20; i >= 0; i-- {
		v := (x >> uint(i)) & 1
		if node.children[v] == nil {
			node.children[v] = newTrie()
		}
		node = node.children[v]
		node.cnt++
	}
}

func (t *Trie) search(x int) int {
	node := t
	ans := 0
	for i := 20; i >= 0; i-- {
		v := (x >> uint(i)) & 1
		if node.children[v^1] != nil && node.children[v^1].cnt > 0 {
			ans |= 1 << uint(i)
			node = node.children[v^1]
		} else {
			node = node.children[v]
		}
	}
	return ans
}

func (t *Trie) remove(x int) {
	node := t
	for i := 20; i >= 0; i-- {
		v := (x >> uint(i)) & 1
		node = node.children[v]
		node.cnt--
	}
}

func maximumStrongPairXor(nums []int) (ans int) {
	sort.Ints(nums)
	tree := newTrie()
	i := 0
	for _, y := range nums {
		tree.insert(y)
		for ; y > nums[i]*2; i++ {
			tree.remove(nums[i])
		}
		ans = max(ans, tree.search(y))
	}
	return ans
}