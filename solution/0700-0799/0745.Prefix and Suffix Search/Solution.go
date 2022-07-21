type Trie struct {
	children [26]*Trie
	indexes  []int
}

func newTrie() *Trie {
	return &Trie{indexes: []int{}}
}
func (this *Trie) insert(word string, i int) {
	node := this
	for _, c := range word {
		idx := c - 'a'
		if node.children[idx] == nil {
			node.children[idx] = newTrie()
		}
		node = node.children[idx]
		node.indexes = append(node.indexes, i)
	}
}

func (this *Trie) search(pref string) []int {
	node := this
	for _, c := range pref {
		idx := c - 'a'
		if node.children[idx] == nil {
			return []int{}
		}
		node = node.children[idx]
	}
	return node.indexes
}

type WordFilter struct {
	p *Trie
	s *Trie
}

func Constructor(words []string) WordFilter {
	p := newTrie()
	s := newTrie()
	for i, w := range words {
		p.insert(w, i)
		s.insert(reverse(w), i)
	}
	return WordFilter{p, s}
}

func (this *WordFilter) F(pref string, suff string) int {
	a := this.p.search(pref)
	b := this.s.search(reverse(suff))
	if len(a) == 0 || len(b) == 0 {
		return -1
	}
	i, j := len(a)-1, len(b)-1
	for i >= 0 && j >= 0 {
		if a[i] == b[j] {
			return a[i]
		}
		if a[i] > b[j] {
			i--
		} else {
			j--
		}
	}
	return -1
}

func reverse(w string) string {
	ww := []byte(w)
	for i, j := 0, len(w)-1; i < j; i++ {
		ww[i], ww[j] = ww[j], ww[i]
		j--
	}
	return string(ww)
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * obj := Constructor(words);
 * param_1 := obj.F(pref,suff);
 */