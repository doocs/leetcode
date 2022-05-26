func shortestAlternatingPaths(n int, redEdges [][]int, blueEdges [][]int) []int {
	get := func(edges [][]int) [][]int {
		res := make([][]int, n)
		for _, e := range edges {
			res[e[0]] = append(res[e[0]], e[1])
		}
		return res
	}
	red := get(redEdges)
	blue := get(blueEdges)
	visBlue := make([]bool, n)
	visRed := make([]bool, n)
	q := [][]int{{0, 1}, {0, 0}}
	ans := make([]int, n)
	for i := range ans {
		ans[i] = -1
	}
	d := -1
	for len(q) > 0 {
		d++
		for t := len(q); t > 0; t-- {
			p := q[0]
			q = q[1:]
			i := p[0]
			b := p[1] == 1
			if ans[i] == -1 || ans[i] > d {
				ans[i] = d
			}
			vis := visRed
			ne := blue[i]
			v := visBlue
			if b {
				vis = visBlue
				ne = red[i]
				v = visRed
			}
			vis[i] = true
			for _, j := range ne {
				if !v[j] {
					v[j] = true
					q = append(q, []int{j, 1 - p[1]})
				}
			}
		}
	}
	return ans
}