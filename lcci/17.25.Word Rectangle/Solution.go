type Trie struct {
	children [26]*Trie
	isEnd    bool
}

func newTrie() *Trie {
	return &Trie{}
}
func (this *Trie) insert(word string) {
	node := this
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
	}
	node.isEnd = true
}

func maxRectangle(words []string) (ans []string) {
	trie := newTrie()
	d := map[int][]string{}
	t := []string{}
	var maxL, maxS int
	for _, w := range words {
		maxL = max(maxL, len(w))
		d[len(w)] = append(d[len(w)], w)
		trie.insert(w)
	}
	check := func(mat []string) int {
		m, n := len(mat), len(mat[0])
		ans := 1
		for j := 0; j < n; j++ {
			node := trie
			for i := 0; i < m; i++ {
				idx := mat[i][j] - 'a'
				if node.children[idx] == nil {
					return 0
				}
				node = node.children[idx]
			}
			if !node.isEnd {
				ans = 2
			}
		}
		return ans
	}
	var dfs func([]string)
	dfs = func(ws []string) {
		if len(ws[0])*maxL <= maxS || len(t) >= maxL {
			return
		}
		for _, w := range ws {
			t = append(t, w)
			st := check(t)
			if st == 0 {
				t = t[:len(t)-1]
				continue
			}
			if st == 1 && maxS < len(t)*len(t[0]) {
				maxS = len(t) * len(t[0])
				ans = append([]string{}, t...)
			}
			dfs(ws)
			t = t[:len(t)-1]
		}
	}
	for _, ws := range d {
		dfs(ws)
	}
	return
}