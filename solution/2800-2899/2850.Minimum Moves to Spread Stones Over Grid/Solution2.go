func minimumMoves(grid [][]int) int {
	left := [][2]int{}
	right := [][2]int{}
	for i := 0; i < 3; i++ {
		for j := 0; j < 3; j++ {
			if grid[i][j] == 0 {
				left = append(left, [2]int{i, j})
			} else {
				for k := 1; k < grid[i][j]; k++ {
					right = append(right, [2]int{i, j})
				}
			}
		}
	}
	cal := func(a, b [2]int) int {
		return abs(a[0]-b[0]) + abs(a[1]-b[1])
	}
	n := len(left)
	f := make([]int, 1<<n)
	f[0] = 0
	for i := 1; i < 1<<n; i++ {
		f[i] = 1 << 30
		k := bits.OnesCount(uint(i))
		for j := 0; j < n; j++ {
			if i>>j&1 == 1 {
				f[i] = min(f[i], f[i^(1<<j)]+cal(left[k-1], right[j]))
			}
		}
	}
	return f[(1<<n)-1]
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}