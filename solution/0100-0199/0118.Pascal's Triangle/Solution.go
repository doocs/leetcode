func generate(numRows int) [][]int {
	f := [][]int{[]int{1}}
	for i := 0; i < numRows-1; i++ {
		g := []int{1}
		for j := 1; j < len(f[i]); j++ {
			g = append(g, f[i][j-1]+f[i][j])
		}
		g = append(g, 1)
		f = append(f, g)
	}
	return f
}