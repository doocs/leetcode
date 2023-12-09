func minSwaps(grid [][]int) (ans int) {
	n := len(grid)
	pos := make([]int, n)
	for i := range pos {
		pos[i] = -1
	}
	for i := 0; i < n; i++ {
		for j := n - 1; j >= 0; j-- {
			if grid[i][j] == 1 {
				pos[i] = j
				break
			}
		}
	}
	for i := 0; i < n; i++ {
		k := -1
		for j := i; j < n; j++ {
			if pos[j] <= i {
				ans += j - i
				k = j
				break
			}
		}
		if k == -1 {
			return -1
		}
		for ; k > i; k-- {
			pos[k], pos[k-1] = pos[k-1], pos[k]
		}
	}
	return
}