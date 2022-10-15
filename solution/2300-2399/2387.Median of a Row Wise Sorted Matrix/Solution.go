func matrixMedian(grid [][]int) int {
	m, n := len(grid), len(grid[0])

	count := func(x int) int {
		cnt := 0
		for _, row := range grid {
			left, right := 0, n
			for left < right {
				mid := (left + right) >> 1
				if row[mid] > x {
					right = mid
				} else {
					left = mid + 1
				}
			}
			cnt += left
		}
		return cnt
	}
	left, right := 0, 1000010
	target := (m*n + 1) >> 1
	for left < right {
		mid := (left + right) >> 1
		if count(mid) >= target {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}