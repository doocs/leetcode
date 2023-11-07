func numMagicSquaresInside(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	check := func(i, j int) int {
		if i+3 > m || j+3 > n {
			return 0
		}
		cnt := [16]int{}
		row := [3]int{}
		col := [3]int{}
		a, b := 0, 0
		for x := i; x < i+3; x++ {
			for y := j; y < j+3; y++ {
				v := grid[x][y]
				if v < 1 || v > 9 || cnt[v] > 0 {
					return 0
				}
				cnt[v]++
				row[x-i] += v
				col[y-j] += v
				if x-i == y-j {
					a += v
				}
				if x-i == 2-(y-j) {
					b += v
				}
			}
		}
		if a != b {
			return 0
		}
		for k := 0; k < 3; k++ {
			if row[k] != a || col[k] != a {
				return 0
			}
		}
		return 1
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			ans += check(i, j)
		}
	}
	return
}