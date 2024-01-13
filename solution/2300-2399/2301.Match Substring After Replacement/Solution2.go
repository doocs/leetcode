func matchReplacement(s string, sub string, mappings [][]byte) bool {
	d := [128][128]bool{}
	for _, e := range mappings {
		d[e[0]][e[1]] = true
	}
	for i := 0; i < len(s)-len(sub)+1; i++ {
		ok := true
		for j := 0; j < len(sub) && ok; j++ {
			a, b := s[i+j], sub[j]
			if a != b && !d[b][a] {
				ok = false
			}
		}
		if ok {
			return true
		}
	}
	return false
}