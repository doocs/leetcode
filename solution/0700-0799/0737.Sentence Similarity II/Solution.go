var p []int

func areSentencesSimilarTwo(sentence1 []string, sentence2 []string, similarPairs [][]string) bool {
	if len(sentence1) != len(sentence2) {
		return false
	}
	n := len(similarPairs)
	p = make([]int, (n<<1)+10)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	words := make(map[string]int)
	idx := 1
	for _, e := range similarPairs {
		a, b := e[0], e[1]
		if words[a] == 0 {
			words[a] = idx
			idx++
		}
		if words[b] == 0 {
			words[b] = idx
			idx++
		}
		p[find(words[a])] = find(words[b])
	}
	for i := 0; i < len(sentence1); i++ {
		if sentence1[i] == sentence2[i] {
			continue
		}
		if words[sentence1[i]] == 0 || words[sentence2[i]] == 0 || find(words[sentence1[i]]) != find(words[sentence2[i]]) {
			return false
		}
	}
	return true
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}