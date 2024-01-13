func generateAbbreviations(word string) []string {
	n := len(word)
	var dfs func(int) []string
	dfs = func(i int) []string {
		if i >= n {
			return []string{""}
		}
		ans := []string{}
		for _, s := range dfs(i + 1) {
			ans = append(ans, word[i:i+1]+s)
		}
		for j := i + 1; j <= n; j++ {
			for _, s := range dfs(j + 1) {
				p := ""
				if j < n {
					p = word[j : j+1]
				}
				ans = append(ans, strconv.Itoa(j-i)+p+s)
			}
		}
		return ans
	}
	return dfs(0)
}