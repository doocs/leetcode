func modifyString(s string) string {
	ans := []byte(s)
	for i, c := range ans {
		if c == '?' {
			for cc := byte('a'); cc <= 'c'; cc++ {
				if i > 0 && ans[i-1] == cc {
					continue
				}
				if i < len(s)-1 && ans[i+1] == cc {
					continue
				}
				ans[i] = cc
				break
			}
		}
	}
	return string(ans)
}