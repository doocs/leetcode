type Trie struct {
	children [2]*Trie
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
	ans := 0
	for i := 30; i >= 0; i-- {
		v := (x >> i) & 1
		if node.children[v^1] != nil {
			node = node.children[v^1]
			ans |= 1 << i
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
	type tuple struct{ i, x, m int }
	n := len(queries)
	qs := make([]tuple, n)
	for i, q := range queries {
		qs[i] = tuple{i, q[0], q[1]}
	}
	sort.Slice(qs, func(i, j int) bool { return qs[i].m < qs[j].m })
	j := 0
	ans := make([]int, n)
	trie := newTrie()
	for _, q := range qs {
		i, x, m := q.i, q.x, q.m
		for j < len(nums) && nums[j] <= m {
			trie.insert(nums[j])
			j++
		}
		ans[i] = trie.search(x)
	}
	return ans
}