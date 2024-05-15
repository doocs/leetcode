func findLadders(beginWord string, endWord string, wordList []string) []string {
	ans := []string{beginWord}
	vis := make([]bool, len(wordList))
	check := func(s, t string) bool {
		if len(s) != len(t) {
			return false
		}
		cnt := 0
		for i := range s {
			if s[i] != t[i] {
				cnt++
			}
		}
		return cnt == 1
	}
	var dfs func(s string) bool
	dfs = func(s string) bool {
		if s == endWord {
			return true
		}
		for i, t := range wordList {
			if !vis[i] && check(s, t) {
				vis[i] = true
				ans = append(ans, t)
				if dfs(t) {
					return true
				}
				ans = ans[:len(ans)-1]
			}
		}
		return false
	}
	if dfs(beginWord) {
		return ans
	}
	return []string{}
}