func minFlips(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	ans := 0

	for i := 0; i < m/2; i++ {
		for j := 0; j < n/2; j++ {
			x, y := m-i-1, n-j-1
			cnt1 := grid[i][j] + grid[x][j] + grid[i][y] + grid[x][y]
			ans += min(cnt1, 4-cnt1)
		}
	}

	if m%2 == 1 && n%2 == 1 {
		ans += grid[m/2][n/2]
	}

	diff, cnt1 := 0, 0

	if m%2 == 1 {
		for j := 0; j < n/2; j++ {
			if grid[m/2][j] == grid[m/2][n-j-1] {
				cnt1 += grid[m/2][j] * 2
			} else {
				diff += 1
			}
		}
	}

	if n%2 == 1 {
		for i := 0; i < m/2; i++ {
			if grid[i][n/2] == grid[m-i-1][n/2] {
				cnt1 += grid[i][n/2] * 2
			} else {
				diff += 1
			}
		}
	}

	if cnt1%4 == 0 || diff > 0 {
		ans += diff
	} else {
		ans += 2
	}

	return ans
}
