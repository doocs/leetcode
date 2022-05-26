func equationsPossible(equations []string) bool {
	p := make([]int, 26)
	for i := 1; i < 26; i++ {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, e := range equations {
		a, b := int(e[0]-'a'), int(e[3]-'a')
		if e[1] == '=' {
			p[find(a)] = find(b)
		}
	}
	for _, e := range equations {
		a, b := int(e[0]-'a'), int(e[3]-'a')
		if e[1] == '!' && find(a) == find(b) {
			return false
		}
	}
	return true
}