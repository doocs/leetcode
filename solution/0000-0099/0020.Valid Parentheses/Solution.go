func isValid(s string) bool {
	stk := []byte{}
	d := map[byte]byte{'(': ')', '[': ']', '{': '}'}
	for i := 0; i < len(s); i++ {
		c := s[i]
		if v, ok := d[c]; ok {
			stk = append(stk, v)
		} else if len(stk) == 0 || stk[len(stk)-1] != c {
			return false
		} else {
			stk = stk[:len(stk)-1]
		}
	}
	return len(stk) == 0
}
