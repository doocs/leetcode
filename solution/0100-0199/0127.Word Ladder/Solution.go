func ladderLength(beginWord string, endWord string, wordList []string) int {
	words := make(map[string]bool)
	for _, word := range wordList {
		words[word] = true
	}
	if !words[endWord] {
		return 0
	}

	q1, q2 := []string{beginWord}, []string{endWord}
	m1, m2 := map[string]int{beginWord: 0}, map[string]int{endWord: 0}
	extend := func() int {
		for i := len(q1); i > 0; i-- {
			s := q1[0]
			step, _ := m1[s]
			q1 = q1[1:]
			chars := []byte(s)
			for j := 0; j < len(chars); j++ {
				ch := chars[j]
				for k := 'a'; k <= 'z'; k++ {
					chars[j] = byte(k)
					t := string(chars)
					if !words[t] {
						continue
					}
					if _, ok := m1[t]; ok {
						continue
					}
					if v, ok := m2[t]; ok {
						return step + 1 + v
					}
					q1 = append(q1, t)
					m1[t] = step + 1
				}
				chars[j] = ch
			}
		}
		return -1
	}
	for len(q1) > 0 && len(q2) > 0 {
		if len(q1) > len(q2) {
			m1, m2 = m2, m1
			q1, q2 = q2, q1
		}
		t := extend()
		if t != -1 {
			return t + 1
		}
	}
	return 0
}