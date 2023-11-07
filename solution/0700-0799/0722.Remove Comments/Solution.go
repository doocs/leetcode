func removeComments(source []string) (ans []string) {
	t := []byte{}
	blockComment := false
	for _, s := range source {
		m := len(s)
		for i := 0; i < m; i++ {
			if blockComment {
				if i+1 < m && s[i] == '*' && s[i+1] == '/' {
					blockComment = false
					i++
				}
			} else {
				if i+1 < m && s[i] == '/' && s[i+1] == '*' {
					blockComment = true
					i++
				} else if i+1 < m && s[i] == '/' && s[i+1] == '/' {
					break
				} else {
					t = append(t, s[i])
				}
			}
		}
		if !blockComment && len(t) > 0 {
			ans = append(ans, string(t))
			t = []byte{}
		}
	}
	return
}