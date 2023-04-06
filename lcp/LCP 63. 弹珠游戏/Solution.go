func ballGame(num int, plate []string) (ans [][]int) {
	dirs := [5]int{0, 1, 0, -1, 0}
	m, n := len(plate), len(plate[0])
	check := func(i, j, d int) bool {
		k := num
		for plate[i][j] != 'O' {
			if k == 0 {
				return false
			}
			if plate[i][j] == 'W' {
				d = (d + 3) % 4
			} else if plate[i][j] == 'E' {
				d = (d + 1) % 4
			}
			i += dirs[d]
			j += dirs[d+1]
			if i < 0 || i >= m || j < 0 || j >= n {
				return false
			}
			k--
		}
		return true
	}
	for i := 1; i < m-1; i++ {
		if plate[i][0] == '.' && check(i, 0, 0) {
			ans = append(ans, []int{i, 0})
		}
		if plate[i][n-1] == '.' && check(i, n-1, 2) {
			ans = append(ans, []int{i, n - 1})
		}
	}
	for j := 1; j < n-1; j++ {
		if plate[0][j] == '.' && check(0, j, 1) {
			ans = append(ans, []int{0, j})
		}
		if plate[m-1][j] == '.' && check(m-1, j, 3) {
			ans = append(ans, []int{m - 1, j})
		}
	}
	return
}