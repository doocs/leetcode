func findWords(board [][]byte, words []string) []string {
	counter := make([]int, 26)
	for _, b := range board {
		for _, c := range b {
			counter[c-'a']++
		}
	}
	s := make(map[string]bool)
	for _, word := range words {
		s[word] = true
	}

	check := func(word string) bool {
		cnt := make([]int, 26)
		for i := range word {
			cnt[word[i]-'a']++
		}
		for i := 0; i < 26; i++ {
			if counter[i] < cnt[i] {
				return false
			}
		}
		return true
	}

	var dfs func(i, j, l int, word string) bool
	dfs = func(i, j, l int, word string) bool {
		if l == len(word) {
			return true
		}
		if i < 0 || i >= len(board) || j < 0 || j >= len(board[0]) || board[i][j] != word[l] {
			return false
		}
		c := board[i][j]
		board[i][j] = '0'
		ans := false
		dirs := []int{-1, 0, 1, 0, -1}
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			ans = ans || dfs(x, y, l+1, word)
		}
		board[i][j] = c
		return ans
	}

	find := func(word string) bool {
		if !check(word) {
			return false
		}
		for i, b := range board {
			for j, _ := range b {
				if dfs(i, j, 0, word) {
					return true
				}
			}
		}
		return false
	}

	var ans []string
	for word, _ := range s {
		if find(word) {
			ans = append(ans, word)
		}
	}
	return ans
}