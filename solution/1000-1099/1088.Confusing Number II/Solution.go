func confusingNumberII(n int) int {
	d := [10]int{0, 1, -1, -1, -1, -1, 9, -1, 8, 6}
	s := strconv.Itoa(n)
	check := func(x int) bool {
		y := 0
		for t := x; t > 0; t /= 10 {
			v := t % 10
			y = y*10 + d[v]
		}
		return x != y
	}
	var dfs func(pos int, limit bool, x int) int
	dfs = func(pos int, limit bool, x int) (ans int) {
		if pos >= len(s) {
			if check(x) {
				return 1
			}
			return 0
		}
		up := 9
		if limit {
			up = int(s[pos] - '0')
		}
		for i := 0; i <= up; i++ {
			if d[i] != -1 {
				ans += dfs(pos+1, limit && i == up, x*10+i)
			}
		}
		return
	}
	return dfs(0, true, 0)
}