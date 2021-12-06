func findLadders(beginWord string, endWord string, wordList []string) []string {
	var ans []string
	visited := make(map[string]bool)

	check := func(a, b string) bool {
		if len(a) != len(b) {
			return false
		}
		cnt := 0
		for i := 0; i < len(a); i++ {
			if a[i] != b[i] {
				cnt++
			}
		}
		return cnt == 1
	}

	var dfs func(begin, end string, t []string)
	dfs = func(begin, end string, t []string) {
		if len(ans) > 0 {
			return
		}
		if begin == end {
			ans = make([]string, len(t))
			copy(ans, t)
			return
		}
		for _, word := range wordList {
			if visited[word] || !check(begin, word) {
				continue
			}
			t = append(t, word)
			visited[word] = true
			dfs(word, end, t)
			t = t[:len(t)-1]
		}
	}

	var t []string
	t = append(t, beginWord)
	dfs(beginWord, endWord, t)
	return ans
}