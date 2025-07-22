func sortMatrix(grid [][]int) [][]int {
	n := len(grid)
	for k := n - 2; k >= 0; k-- {
		i, j := k, 0
		t := []int{}
		for ; i < n && j < n; i, j = i+1, j+1 {
			t = append(t, grid[i][j])
		}
		sort.Ints(t)
		for _, x := range t {
			i, j = i-1, j-1
			grid[i][j] = x
		}
	}
	for k := n - 2; k > 0; k-- {
		i, j := k, n-1
		t := []int{}
		for ; i >= 0 && j >= 0; i, j = i-1, j-1 {
			t = append(t, grid[i][j])
		}
		sort.Ints(t)
		for _, x := range t {
			i, j = i+1, j+1
			grid[i][j] = x
		}
	}
	return grid
}
