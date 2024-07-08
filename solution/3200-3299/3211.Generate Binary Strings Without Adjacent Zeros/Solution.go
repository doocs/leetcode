func validStrings(n int) (ans []string) {
	t := []byte{}
	var dfs func(int)
	dfs = func(i int) {
		if i >= n {
			ans = append(ans, string(t))
			return
		}
		for j := 0; j < 2; j++ {
			if (j == 0 && (i == 0 || t[i-1] == '1')) || j == 1 {
				t = append(t, byte('0'+j))
				dfs(i + 1)
				t = t[:len(t)-1]
			}
		}
	}
	dfs(0)
	return
}