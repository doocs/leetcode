func minOperations(grid [][]int, k int) int64 {
	m, n := len(grid), len(grid[0])
	maxVal := grid[0][0]
	for _, row := range grid {
		maxVal = max(maxVal, slices.Max(row))
	}

	check := func(target int) int64 {
		diff := make([][]int64, m+2)
		for i := range diff {
			diff[i] = make([]int64, n+2)
		}
		var totalOps int64

		for i := 1; i <= m; i++ {
			for j := 1; j <= n; j++ {
				diff[i][j] += diff[i-1][j] + diff[i][j-1] - diff[i-1][j-1]
				curVal := int64(grid[i-1][j-1]) + diff[i][j]

				if curVal > int64(target) {
					return -1
				}

				if curVal < int64(target) {
					if i+k-1 > m || j+k-1 > n {
						return -1
					}
					needed := int64(target) - curVal
					totalOps += needed
					diff[i][j] += needed
					diff[i+k][j] -= needed
					diff[i][j+k] -= needed
					diff[i+k][j+k] += needed
				}
			}
		}
		return totalOps
	}

	for t := maxVal; t <= maxVal+1; t++ {
		if res := check(t); res != -1 {
			return res
		}
	}

	return -1
}
