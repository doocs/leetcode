type Trie struct {
	children [26]*Trie
	cnt      int
}

func (t *Trie) insert(w string) {
	node := t
	for _, c := range w {
		idx := c - 'a'
		if node.children[idx] == nil {
			node.children[idx] = &Trie{}
		}
		node = node.children[idx]
		node.cnt++
	}
}

func (t *Trie) search(w string) int {
	node := t
	ans := 0
	for _, c := range w {
		ans++
		idx := c - 'a'
		node = node.children[idx]
		if node.cnt == 1 {
			return ans
		}
	}
	return len(w)
}

func wordsAbbreviation(words []string) (ans []string) {
	tries := make(map[[2]int]*Trie)
	for _, w := range words {
		key := [2]int{len(w), int(w[len(w)-1] - 'a')}
		_, exists := tries[key]
		if !exists {
			tries[key] = &Trie{}
		}
		tries[key].insert(w)
	}

	for _, w := range words {
		m := len(w)
		key := [2]int{m, int(w[m-1] - 'a')}
		cnt := tries[key].search(w)
		if cnt+2 >= m {
			ans = append(ans, w)
		} else {
			abbr := w[:cnt] + fmt.Sprintf("%d", m-cnt-1) + w[m-1:]
			ans = append(ans, abbr)
		}
	}
	return
}