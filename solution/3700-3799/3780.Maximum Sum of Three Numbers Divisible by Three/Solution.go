func maximumSum(nums []int) int {
	sort.Ints(nums)
	g := make([][]int, 3)
	for _, x := range nums {
		g[x%3] = append(g[x%3], x)
	}
	ans := 0
	for a := 0; a < 3; a++ {
		if len(g[a]) > 0 {
			x := g[a][len(g[a])-1]
			g[a] = g[a][:len(g[a])-1]
			for b := 0; b < 3; b++ {
				if len(g[b]) > 0 {
					y := g[b][len(g[b])-1]
					g[b] = g[b][:len(g[b])-1]
					c := (3 - (a+b)%3) % 3
					if len(g[c]) > 0 {
						z := g[c][len(g[c])-1]
						ans = max(ans, x+y+z)
					}
					g[b] = append(g[b], y)
				}
			}
			g[a] = append(g[a], x)
		}
	}
	return ans
}
