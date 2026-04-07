func canPartitionGrid(grid [][]int) bool {
	return check(grid) || check(rotate(grid))
}

func check(g [][]int) bool {
	m, n := len(g), len(g[0])
	var s1, s2 int64

	cnt1 := map[int64]int{}
	cnt2 := map[int64]int{}

	for _, row := range g {
		for _, x := range row {
			v := int64(x)
			s2 += v
			cnt2[v]++
		}
	}

	for i := 0; i < m-1; i++ {
		for _, x := range g[i] {
			v := int64(x)
			s1 += v
			s2 -= v
			cnt1[v]++
			cnt2[v]--
		}

		if s1 == s2 {
			return true
		}

		if s1 < s2 {
			diff := s2 - s1
			if cnt2[diff] > 0 {
				if (m-i-1 > 1 && n > 1) ||
					(i == m-2 && (int64(g[i+1][0]) == diff || int64(g[i+1][n-1]) == diff)) ||
					(n == 1 && (int64(g[i+1][0]) == diff || int64(g[m-1][0]) == diff)) {
					return true
				}
			}
		} else {
			diff := s1 - s2
			if cnt1[diff] > 0 {
				if (i+1 > 1 && n > 1) ||
					(i == 0 && (int64(g[0][0]) == diff || int64(g[0][n-1]) == diff)) ||
					(n == 1 && (int64(g[0][0]) == diff || int64(g[i][0]) == diff)) {
					return true
				}
			}
		}
	}

	return false
}

func rotate(grid [][]int) [][]int {
	m, n := len(grid), len(grid[0])
	t := make([][]int, n)
	for i := range t {
		t[i] = make([]int, m)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			t[j][i] = grid[i][j]
		}
	}
	return t
}
