func numBusesToDestination(routes [][]int, source int, target int) int {
	if source == target {
		return 0
	}
	n := len(routes)
	s := make([]map[int]bool, n)
	g := make([][]int, n)
	d := map[int][]int{}
	for i, r := range routes {
		for _, v := range r {
			if s[i] == nil {
				s[i] = make(map[int]bool)
			}
			s[i][v] = true
			d[v] = append(d[v], i)
		}
	}
	for _, ids := range d {
		m := len(ids)
		for i := 0; i < m; i++ {
			for j := i + 1; j < m; j++ {
				a, b := ids[i], ids[j]
				g[a] = append(g[a], b)
				g[b] = append(g[b], a)
			}
		}
	}
	q := d[source]
	vis := map[int]bool{}
	for _, v := range d[source] {
		vis[v] = true
	}
	ans := 1
	for len(q) > 0 {
		for k := len(q); k > 0; k-- {
			i := q[0]
			q = q[1:]
			if s[i][target] {
				return ans
			}
			for _, j := range g[i] {
				if !vis[j] {
					vis[j] = true
					q = append(q, j)
				}
			}
		}
		ans++
	}
	return -1
}