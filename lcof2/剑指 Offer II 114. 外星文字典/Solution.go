func alienOrder(words []string) string {
	n := len(words)
	if n == 0 {
		return ""
	}
	if n == 1 {
		return words[0]
	}
	inDegree := make(map[byte]int)
	graph := make(map[byte][]byte)
	for _, word := range words {
		for i := 0; i < len(word); i++ {
			inDegree[word[i]] = 0
		}
	}
	for i := 0; i < n-1; i++ {
		w1, w2 := words[i], words[i+1]
		minLen := len(w1)
		if len(w2) < minLen {
			minLen = len(w2)
		}
		foundDifference := false
		for j := 0; j < minLen; j++ {
			if w1[j] != w2[j] {
				inDegree[w2[j]]++
				graph[w1[j]] = append(graph[w1[j]], w2[j])
				foundDifference = true
				break
			}
		}
		if !foundDifference && len(w1) > len(w2) {
			return ""
		}
	}
	queue := make([]byte, 0)
	for k := range inDegree {
		if inDegree[k] == 0 {
			queue = append(queue, k)
		}
	}
	res := make([]byte, 0)
	for len(queue) > 0 {
		node := queue[0]
		queue = queue[1:]
		res = append(res, node)
		for _, next := range graph[node] {
			inDegree[next]--
			if inDegree[next] == 0 {
				queue = append(queue, next)
			}
		}
	}
	if len(res) != len(inDegree) {
		return ""
	}
	return string(res)
}