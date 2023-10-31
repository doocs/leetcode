func maxIncreasingCells(mat [][]int) (ans int) {
	m, n := len(mat), len(mat[0])
	g := map[int][][2]int{}
	for i, row := range mat {
		for j, v := range row {
			g[v] = append(g[v], [2]int{i, j})
		}
	}
	nums := make([]int, 0, len(g))
	for k := range g {
		nums = append(nums, k)
	}
	sort.Ints(nums)
	rowMax := make([]int, m)
	colMax := make([]int, n)
	for _, k := range nums {
		pos := g[k]
		mx := make([]int, len(pos))
		for i, p := range pos {
			mx[i] = max(rowMax[p[0]], colMax[p[1]]) + 1
			ans = max(ans, mx[i])
		}
		for i, p := range pos {
			rowMax[p[0]] = max(rowMax[p[0]], mx[i])
			colMax[p[1]] = max(colMax[p[1]], mx[i])
		}
	}
	return
}