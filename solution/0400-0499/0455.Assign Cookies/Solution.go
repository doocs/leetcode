func findContentChildren(g []int, s []int) int {
	sort.Ints(g)
	sort.Ints(s)
	j := 0
	for i, x := range g {
		for j < len(s) && s[j] < x {
			j++
		}
		if j >= len(s) {
			return i
		}
		j++
	}
	return len(g)
}