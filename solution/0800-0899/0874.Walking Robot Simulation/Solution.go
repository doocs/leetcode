func robotSim(commands []int, obstacles [][]int) int {
	dirs := [][]int{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}
	s := map[string]bool{}
	for _, v := range obstacles {
		t := strconv.Itoa(v[0]) + "." + strconv.Itoa(v[1])
		s[t] = true
	}
	ans, p := 0, 1
	x, y := 0, 0
	for _, v := range commands {
		if v == -2 {
			p = (p + 3) % 4
		} else if v == -1 {
			p = (p + 1) % 4
		} else {
			for i := 0; i < v; i++ {
				nx, ny := x+dirs[p][0], y+dirs[p][1]
				t := strconv.Itoa(nx) + "." + strconv.Itoa(ny)
				if s[t] {
					break
				}
				x, y = nx, ny
				ans = max(ans, x*x+y*y)
			}
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}