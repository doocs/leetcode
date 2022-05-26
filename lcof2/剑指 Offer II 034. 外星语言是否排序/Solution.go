func isAlienSorted(words []string, order string) bool {
	index := make(map[byte]int)
	for i := range order {
		index[order[i]] = i
	}
	for i := 0; i < len(words)-1; i++ {
		w1, w2 := words[i], words[i+1]
		l1, l2 := len(w1), len(w2)
		flag := true
		for j := 0; j < min(l1, l2) && flag; j++ {
			i1, i2 := index[w1[j]], index[w2[j]]
			if i1 > i2 {
				return false
			}
			if i1 < i2 {
				flag = false
			}
		}
		if flag && l1 > l2 {
			return false
		}
	}
	return true
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}