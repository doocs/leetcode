type Trie struct {
	children [26]*Trie
	w        string
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
	node.w = word
}

func findWords(board [][]byte, words []string) []string {
	trie := newTrie()
	for _, w := range words {
		trie.insert(w)
	}
	m, n := len(board), len(board[0])
	res := map[string]bool{}
	var dfs func(node *Trie, i, j int)
	dfs = func(node *Trie, i, j int) {
		idx := board[i][j] - 'a'
		if node.children[idx] == nil {
			return
		}
		node = node.children[idx]
		if node.w != "" {
			res[node.w] = true
		}
		dirs := []int{-1, 0, 1, 0, -1}
		c := board[i][j]
		board[i][j] = '0'
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '0' {
				dfs(node, x, y)
			}
		}
		board[i][j] = c
	}
	for i, row := range board {
		for j := range row {
			dfs(trie, i, j)
		}
	}
	var ans []string
	for v := range res {
		ans = append(ans, v)
	}
	return ans
}