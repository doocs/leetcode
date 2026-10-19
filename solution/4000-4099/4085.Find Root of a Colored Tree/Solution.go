func findColoredRoot(edges [][]int, colors string) int {
	d := [128]int{'W': 0, 'R': 1, 'B': 2}
	n := len(colors)
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	ans := -1
	for u, vs := range g {
		if len(vs) > 3 {
			return -1
		}
		cnt := [3]int{}
		for _, v := range vs {
			cnt[d[colors[v]]]++
		}
		c := d[colors[u]]
		if cnt[c] > 0 || cnt[(c+2)%3] > 1 {
			return -1
		}
		if len(vs) < 3 && cnt[(c+2)%3] == 0 {
			ans = u
		}
	}
	return ans
}
