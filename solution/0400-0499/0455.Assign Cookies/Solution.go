func findContentChildren(g []int, s []int) int {
	sort.Ints(g)
	sort.Ints(s)
	i, j := 0, 0
	for ; i < len(g); i++ {
		for ; j < len(s) && s[j] < g[i]; j++ {
		}
		if j >= len(s) {
			break
		}
		j++
	}
	return i
}