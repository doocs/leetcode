func minimumJumps(forbidden []int, a int, b int, x int) int {
	n := 6010
	s := make(map[int]bool)
	for _, v := range forbidden {
		s[v] = true
	}
	q := [][]int{[]int{0, 0}}
	vis := make([][]bool, n)
	for i := range vis {
		vis[i] = make([]bool, 2)
	}
	vis[0][0] = true
	vis[0][1] = true
	ans := 0
	for len(q) > 0 {
		for t := len(q); t > 0; t-- {
			p := q[0]
			q = q[1:]
			i, dir := p[0], p[1]
			if i == x {
				return ans
			}
			nxt := [][]int{[]int{i + a, 1}}
			if dir != 0 {
				nxt = append(nxt, []int{i - b, 0})
			}
			for _, e := range nxt {
				j := e[0]
				dir = e[1]
				if j >= 0 && j < n && !s[j] && !vis[j][dir] {
					vis[j][dir] = true
					q = append(q, []int{j, dir})
				}
			}
		}
		ans++
	}
	return -1
}