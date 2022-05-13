func findLexSmallestString(s string, a int, b int) string {
	q := []string{s}
	vis := map[string]bool{s: true}
	ans := s
	for len(q) > 0 {
		s = q[0]
		q = q[1:]
		if s < ans {
			ans = s
		}
		for _, nxt := range []string{op1(s, a), op2(s, b)} {
			if !vis[nxt] {
				vis[nxt] = true
				q = append(q, nxt)
			}
		}
	}
	return ans
}

func op1(s string, a int) string {
	res := []byte(s)
	for i := 1; i < len(s); i += 2 {
		res[i] = byte((int(res[i]-'0')+a)%10 + '0')
	}
	return string(res)
}

func op2(s string, b int) string {
	return s[len(s)-b:] + s[:len(s)-b]
}