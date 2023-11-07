func rotateGrid(grid [][]int, k int) [][]int {
	m, n := len(grid), len(grid[0])

	rotate := func(p, k int) {
		nums := []int{}
		for j := p; j < n-p-1; j++ {
			nums = append(nums, grid[p][j])
		}
		for i := p; i < m-p-1; i++ {
			nums = append(nums, grid[i][n-p-1])
		}
		for j := n - p - 1; j > p; j-- {
			nums = append(nums, grid[m-p-1][j])
		}
		for i := m - p - 1; i > p; i-- {
			nums = append(nums, grid[i][p])
		}
		l := len(nums)
		k %= l
		if k == 0 {
			return
		}
		for j := p; j < n-p-1; j++ {
			grid[p][j] = nums[k]
			k = (k + 1) % l
		}
		for i := p; i < m-p-1; i++ {
			grid[i][n-p-1] = nums[k]
			k = (k + 1) % l
		}
		for j := n - p - 1; j > p; j-- {
			grid[m-p-1][j] = nums[k]
			k = (k + 1) % l
		}
		for i := m - p - 1; i > p; i-- {
			grid[i][p] = nums[k]
			k = (k + 1) % l
		}
	}

	for i := 0; i < m/2 && i < n/2; i++ {
		rotate(i, k)
	}
	return grid
}