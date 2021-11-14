var p []int

func friendRequests(n int, restrictions [][]int, requests [][]int) []bool {
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	var ans []bool
	for _, req := range requests {
		u, v := req[0], req[1]
		if find(u) == find(v) {
			ans = append(ans, true)
		} else {
			valid := true
			for _, res := range restrictions {
				x, y := res[0], res[1]
				if (find(u) == find(x) && find(v) == find(y)) || (find(u) == find(y) && find(v) == find(x)) {
					valid = false
					break
				}
			}
			ans = append(ans, valid)
			if valid {
				p[find(u)] = find(v)
			}
		}
	}
	return ans
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}