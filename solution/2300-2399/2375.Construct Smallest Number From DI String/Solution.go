func smallestNumber(pattern string) string {
	vis := make([]bool, 10)
	t := []byte{}
	ans := ""
	var dfs func(u int)
	dfs = func(u int) {
		if ans != "" {
			return
		}
		if u == len(pattern)+1 {
			ans = string(t)
			return
		}
		for i := 1; i < 10; i++ {
			if !vis[i] {
				if u > 0 && pattern[u-1] == 'I' && int(t[len(t)-1]-'0') >= i {
					continue
				}
				if u > 0 && pattern[u-1] == 'D' && int(t[len(t)-1]-'0') <= i {
					continue
				}
				vis[i] = true
				t = append(t, byte('0'+i))
				dfs(u + 1)
				vis[i] = false
				t = t[:len(t)-1]
			}
		}
	}
	dfs(0)
	return ans
}