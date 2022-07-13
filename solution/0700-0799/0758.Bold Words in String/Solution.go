type Trie struct {
	children [128]*Trie
	isEnd    bool
}

func newTrie() *Trie {
	return &Trie{}
}

func (this *Trie) insert(word string) {
	node := this
	for _, c := range word {
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
	}
	node.isEnd = true
}

func boldWords(words []string, s string) string {
	trie := newTrie()
	for _, w := range words {
		trie.insert(w)
	}
	n := len(s)
	var pairs [][]int
	for i := range s {
		node := trie
		for j := i; j < n; j++ {
			if node.children[s[j]] == nil {
				break
			}
			node = node.children[s[j]]
			if node.isEnd {
				pairs = append(pairs, []int{i, j})
			}
		}
	}
	if len(pairs) == 0 {
		return s
	}
	var t [][]int
	st, ed := pairs[0][0], pairs[0][1]
	for i := 1; i < len(pairs); i++ {
		a, b := pairs[i][0], pairs[i][1]
		if ed+1 < a {
			t = append(t, []int{st, ed})
			st, ed = a, b
		} else {
			ed = max(ed, b)
		}
	}
	t = append(t, []int{st, ed})
	var ans strings.Builder
	i, j := 0, 0
	for i < n {
		if j == len(t) {
			ans.WriteString(s[i:])
			break
		}
		st, ed = t[j][0], t[j][1]
		if i < st {
			ans.WriteString(s[i:st])
		}
		ans.WriteString("<b>")
		ans.WriteString(s[st : ed+1])
		ans.WriteString("</b>")
		i = ed + 1
		j++
	}
	return ans.String()
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}