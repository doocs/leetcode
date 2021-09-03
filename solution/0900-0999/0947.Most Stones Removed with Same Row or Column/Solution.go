var p []int

func removeStones(stones [][]int) int {
	n := 10010
	p = make([]int, n<<1)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	for _, e := range stones {
		p[find(e[0])] = find(e[1] + n)
	}
	s := make(map[int]bool)
	for _, e := range stones {
		s[find(e[0])] = true
	}
	return len(stones) - len(s)
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}