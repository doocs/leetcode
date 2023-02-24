type Trie struct {
	children [26]*Trie
	v        []int
}

func newTrie() *Trie {
	return &Trie{}
}
func (this *Trie) insert(w string, i int) {
	node := this
	for _, c := range w {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
		if len(node.v) < 3 {
			node.v = append(node.v, i)
		}
	}
}

func (this *Trie) search(w string) [][]int {
	node := this
	n := len(w)
	ans := make([][]int, n)
	for i, c := range w {
		c -= 'a'
		if node.children[c] == nil {
			break
		}
		node = node.children[c]
		ans[i] = node.v
	}
	return ans
}

func suggestedProducts(products []string, searchWord string) (ans [][]string) {
	sort.Strings(products)
	trie := newTrie()
	for i, w := range products {
		trie.insert(w, i)
	}
	for _, v := range trie.search(searchWord) {
		t := []string{}
		for _, i := range v {
			t = append(t, products[i])
		}
		ans = append(ans, t)
	}
	return
}