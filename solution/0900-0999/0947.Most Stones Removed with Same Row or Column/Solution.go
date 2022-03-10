func removeStones(stones [][]int) int {
	n := 10010
	p := make([]int, n<<1)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, stone := range stones {
		p[find(stone[0])] = find(stone[1] + n)
	}
	s := make(map[int]bool)
	for _, stone := range stones {
		s[find(stone[0])] = true
	}
	return len(stones) - len(s)
}