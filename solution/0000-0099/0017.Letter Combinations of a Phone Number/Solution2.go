func letterCombinations(digits string) (ans []string) {
	d := []string{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"}
	t := []rune{}
	var dfs func(int)
	dfs = func(i int) {
		if i >= len(digits) {
			ans = append(ans, string(t))
			return
		}
		for _, c := range d[digits[i]-'2'] {
			t = append(t, c)
			dfs(i + 1)
			t = t[:len(t)-1]
		}
	}
	if len(digits) == 0 {
		return
	}
	dfs(0)
	return
}