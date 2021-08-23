var p []int

func equationsPossible(equations []string) bool {
	p = make([]int, 26)
	for i := 1; i < 26; i++ {
		p[i] = i
	}
	for _, e := range equations {
		a, b := int(e[0]-'a'), int(e[3]-'a')
		r := e[1]
		if r == '=' {
			p[find(a)] = find(b)
		}
	}
	for _, e := range equations {
		a, b := int(e[0]-'a'), int(e[3]-'a')
		r := e[1]
		if r == '!' && find(a) == find(b) {
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