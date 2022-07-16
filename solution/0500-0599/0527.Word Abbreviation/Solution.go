type Trie struct {
	children [26]*Trie
	v        [26]int
}

func newTrie() *Trie {
	return &Trie{}
}
func (this *Trie) insert(w string) {
	node := this
	t := w[len(w)-1] - 'a'
	for _, c := range w {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
		node.v[t]++
	}
}
func (this *Trie) search(w string) string {
	node := this
	t := w[len(w)-1] - 'a'
	res := &strings.Builder{}
	for _, c := range w[:len(w)-1] {
		res.WriteRune(c)
		c -= 'a'
		node = node.children[c]
		if node.v[t] == 1 {
			break
		}
	}
	n := len(w) - res.Len() - 1
	if n > 0 {
		res.WriteString(strconv.Itoa(n))
	}
	res.WriteByte(w[len(w)-1])
	if res.Len() < len(w) {
		return res.String()
	}
	return w
}

func wordsAbbreviation(words []string) []string {
	trees := map[int]*Trie{}
	for _, w := range words {
		if _, ok := trees[len(w)]; !ok {
			trees[len(w)] = newTrie()
		}
	}
	for _, w := range words {
		trees[len(w)].insert(w)
	}
	ans := []string{}
	for _, w := range words {
		ans = append(ans, trees[len(w)].search(w))
	}
	return ans
}