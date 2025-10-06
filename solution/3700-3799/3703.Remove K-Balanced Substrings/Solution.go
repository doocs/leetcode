func removeSubstring(s string, k int) string {
	type pair struct {
		ch    byte
		count int
	}
	stk := make([]pair, 0)
	for i := 0; i < len(s); i++ {
		c := s[i]
		if len(stk) > 0 && stk[len(stk)-1].ch == c {
			stk[len(stk)-1].count++
		} else {
			stk = append(stk, pair{c, 1})
		}
		if c == ')' && len(stk) > 1 {
			top := &stk[len(stk)-1]
			prev := &stk[len(stk)-2]
			if top.count == k && prev.count >= k {
				stk = stk[:len(stk)-1]
				prev.count -= k
				if prev.count == 0 {
					stk = stk[:len(stk)-1]
				}
			}
		}
	}
	res := make([]byte, 0)
	for _, p := range stk {
		for i := 0; i < p.count; i++ {
			res = append(res, p.ch)
		}
	}
	return string(res)
}
