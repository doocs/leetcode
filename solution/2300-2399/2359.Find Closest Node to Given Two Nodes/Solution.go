func closestMeetingNode(edges []int, node1 int, node2 int) int {
	n := len(edges)
	g := make([][]int, n)
	for i, j := range edges {
		if j != -1 {
			g[i] = append(g[i], j)
		}
	}
	const inf int = 1 << 30
	f := func(i int) []int {
		dist := make([]int, n)
		for j := range dist {
			dist[j] = inf
		}
		dist[i] = 0
		q := []int{i}
		for len(q) > 0 {
			i = q[0]
			q = q[1:]
			for _, j := range g[i] {
				if dist[j] == inf {
					dist[j] = dist[i] + 1
					q = append(q, j)
				}
			}
		}
		return dist
	}
	d1 := f(node1)
	d2 := f(node2)
	ans, d := -1, inf
	for i, a := range d1 {
		b := d2[i]
		t := max(a, b)
		if t < d {
			d = t
			ans = i
		}
	}
	return ans
}