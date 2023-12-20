type Trie struct {
	children [2]*Trie
}

func NewTrie() *Trie {
	return &Trie{}
}

func (t *Trie) insert(x int) {
	node := t
	for i := 30; i >= 0; i-- {
		v := x >> i & 1
		if node.children[v] == nil {
			node.children[v] = NewTrie()
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
		} else if node.children[v] != nil {
			node = node.children[v]
		} else {
			return -1
		}
	}
	return ans
}

func maximizeXor(nums []int, queries [][]int) []int {
	sort.Ints(nums)
	n := len(queries)
	idx := make([]int, n)
	for i := 0; i < n; i++ {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool {
		return queries[idx[i]][1] < queries[idx[j]][1]
	})
	ans := make([]int, n)
	trie := NewTrie()
	j := 0
	for _, i := range idx {
		x, m := queries[i][0], queries[i][1]
		for j < len(nums) && nums[j] <= m {
			trie.insert(nums[j])
			j++
		}
		ans[i] = trie.search(x)
	}
	return ans
}