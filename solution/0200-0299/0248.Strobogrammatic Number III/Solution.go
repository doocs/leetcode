func strobogrammaticInRange(low string, high string) int {
	n := 0
	var dfs func(int) []string
	dfs = func(u int) []string {
		if u == 0 {
			return []string{""}
		}
		if u == 1 {
			return []string{"0", "1", "8"}
		}
		var ans []string
		pairs := [][]string{{"1", "1"}, {"8", "8"}, {"6", "9"}, {"9", "6"}}
		for _, v := range dfs(u - 2) {
			for _, p := range pairs {
				ans = append(ans, p[0]+v+p[1])
			}
			if u != n {
				ans = append(ans, "0"+v+"0")
			}
		}
		return ans
	}
	a, b := len(low), len(high)
	l, _ := strconv.Atoi(low)
	r, _ := strconv.Atoi(high)
	ans := 0
	for n = a; n <= b; n++ {
		for _, s := range dfs(n) {
			v, _ := strconv.Atoi(s)
			if l <= v && v <= r {
				ans++
			}
		}
	}
	return ans
}