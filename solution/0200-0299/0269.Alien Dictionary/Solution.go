func alienOrder(words []string) string {
	g := [26][26]bool{}
	s := [26]bool{}
	cnt := 0
	n := len(words)
	for i := 0; i < n-1; i++ {
		for _, c := range words[i] {
			if cnt == 26 {
				break
			}
			c -= 'a'
			if !s[c] {
				cnt++
				s[c] = true
			}
		}
		m := len(words[i])
		for j := 0; j < m; j++ {
			if j >= len(words[i+1]) {
				return ""
			}
			c1, c2 := words[i][j]-'a', words[i+1][j]-'a'
			if c1 == c2 {
				continue
			}
			if g[c2][c1] {
				return ""
			}
			g[c1][c2] = true
			break
		}
	}
	for _, c := range words[n-1] {
		if cnt == 26 {
			break
		}
		c -= 'a'
		if !s[c] {
			cnt++
			s[c] = true
		}
	}

	inDegree := [26]int{}
	for _, out := range g {
		for i, v := range out {
			if v {
				inDegree[i]++
			}
		}
	}
	q := []int{}
	for i, in := range inDegree {
		if in == 0 && s[i] {
			q = append(q, i)
		}
	}
	ans := ""
	for len(q) > 0 {
		t := q[0]
		q = q[1:]
		ans += string(t + 'a')
		for i, v := range g[t] {
			if v {
				inDegree[i]--
				if inDegree[i] == 0 && s[i] {
					q = append(q, i)
				}
			}
		}
	}
	if len(ans) < cnt {
		return ""
	}
	return ans
}
