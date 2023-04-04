type Trie struct {
	children [26]*Trie
	ref      int
}

func newTrie() *Trie {
	return &Trie{ref: -1}
}
func (this *Trie) insert(w string, ref int) {
	node := this
	for _, c := range w {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
	}
	node.ref = ref
}

func findWords(board [][]byte, words []string) (ans []string) {
	trie := newTrie()
	for i, w := range words {
		trie.insert(w, i)
	}
	m, n := len(board), len(board[0])
	var dfs func(*Trie, int, int)
	dfs = func(node *Trie, i, j int) {
		idx := board[i][j] - 'a'
		if node.children[idx] == nil {
			return
		}
		node = node.children[idx]
		if node.ref != -1 {
			ans = append(ans, words[node.ref])
			node.ref = -1
		}
		c := board[i][j]
		board[i][j] = '#'
		dirs := [5]int{-1, 0, 1, 0, -1}
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '#' {
				dfs(node, x, y)
			}
		}
		board[i][j] = c
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			dfs(trie, i, j)
		}
	}
	return
}