func friendRequests(n int, restrictions [][]int, requests [][]int) (ans []bool) {
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, req := range requests {
		pu, pv := find(req[0]), find(req[1])
		if pu == pv {
			ans = append(ans, true)
		} else {
			ok := true
			for _, r := range restrictions {
				px, py := find(r[0]), find(r[1])
				if px == pu && py == pv || px == pv && py == pu {
					ok = false
					break
				}
			}
			ans = append(ans, ok)
			if ok {
				p[pv] = pu
			}
		}
	}
	return
}