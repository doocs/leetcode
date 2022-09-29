func pyramidTransition(bottom string, allowed []string) bool {
	f := make([][]int, 7)
	for i := range f {
		f[i] = make([]int, 7)
	}
	for _, s := range allowed {
		a, b := s[0]-'A', s[1]-'A'
		f[a][b] |= 1 << (s[2] - 'A')
	}
	dp := map[string]bool{}
	var dfs func(s string, t []byte) bool
	dfs = func(s string, t []byte) bool {
		if len(s) == 1 {
			return true
		}
		if len(t)+1 == len(s) {
			return dfs(string(t), []byte{})
		}
		k := s + "." + string(t)
		if v, ok := dp[k]; ok {
			return v
		}
		a, b := s[len(t)]-'A', s[len(t)+1]-'A'
		cs := f[a][b]
		for i := 0; i < 7; i++ {
			if ((cs >> i) & 1) == 1 {
				t = append(t, byte('A'+i))
				if dfs(s, t) {
					dp[k] = true
					return true
				}
				t = t[:len(t)-1]
			}
		}
		dp[k] = false
		return false
	}
	return dfs(bottom, []byte{})
}