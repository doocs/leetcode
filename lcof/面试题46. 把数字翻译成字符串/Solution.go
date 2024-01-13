func translateNum(num int) int {
	s := strconv.Itoa(num)
	n := len(s)
	f := [12]int{}
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n-1 {
			return 1
		}
		if f[i] != 0 {
			return f[i]
		}
		ans := dfs(i + 1)
		if s[i] == '1' || (s[i] == '2' && s[i+1] < '6') {
			ans += dfs(i + 2)
		}
		f[i] = ans
		return ans
	}
	return dfs(0)
}