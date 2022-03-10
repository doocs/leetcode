func findLadders(beginWord string, endWord string, wordList []string) [][]string {
	var ans [][]string
	words := make(map[string]bool)
	for _, word := range wordList {
		words[word] = true
	}
	if !words[endWord] {
		return ans
	}
	words[beginWord] = false
	dist := map[string]int{beginWord: 0}
	prev := map[string]map[string]bool{}
	q := []string{beginWord}
	found := false
	step := 0
	for len(q) > 0 && !found {
		step++
		for i := len(q); i > 0; i-- {
			p := q[0]
			q = q[1:]
			chars := []byte(p)
			for j := 0; j < len(chars); j++ {
				ch := chars[j]
				for k := 'a'; k <= 'z'; k++ {
					chars[j] = byte(k)
					t := string(chars)
					if v, ok := dist[t]; ok {
						if v == step {
							prev[t][p] = true
						}
					}
					if !words[t] {
						continue
					}
					if len(prev[t]) == 0 {
						prev[t] = make(map[string]bool)
					}
					prev[t][p] = true
					words[t] = false
					q = append(q, t)
					dist[t] = step
					if endWord == t {
						found = true
					}
				}
				chars[j] = ch
			}
		}
	}
	var dfs func(path []string, begin, cur string)
	dfs = func(path []string, begin, cur string) {
		if cur == beginWord {
			cp := make([]string, len(path))
			copy(cp, path)
			ans = append(ans, cp)
			return
		}
		for k := range prev[cur] {
			path = append([]string{k}, path...)
			dfs(path, beginWord, k)
			path = path[1:]
		}
	}
	if found {
		path := []string{endWord}
		dfs(path, beginWord, endWord)
	}
	return ans
}