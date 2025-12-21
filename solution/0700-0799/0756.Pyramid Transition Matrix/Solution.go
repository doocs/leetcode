func pyramidTransition(bottom string, allowed []string) bool {
	d := make([][]int, 7)
	for i := 0; i < 7; i++ {
		d[i] = make([]int, 7)
	}

	for _, s := range allowed {
		a := int(s[0] - 'A')
		b := int(s[1] - 'A')
		c := int(s[2] - 'A')
		d[a][b] |= 1 << c
	}

	f := make(map[string]bool)

	var dfs func(s string, t []byte) bool
	dfs = func(s string, t []byte) bool {
		if len(s) == 1 {
			return true
		}
		if len(t)+1 == len(s) {
			return dfs(string(t), []byte{})
		}

		key := s + "." + string(t)
		if v, ok := f[key]; ok {
			return v
		}

		i := len(t)
		a := int(s[i] - 'A')
		b := int(s[i+1] - 'A')
		cs := d[a][b]

		for c := 0; c < 7; c++ {
			if (cs>>c)&1 == 1 {
				t = append(t, byte('A'+c))
				if dfs(s, t) {
					f[key] = true
					return true
				}
				t = t[:len(t)-1]
			}
		}

		f[key] = false
		return false
	}

	return dfs(bottom, []byte{})
}
