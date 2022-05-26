func executeInstructions(n int, startPos []int, s string) []int {
	m := len(s)
	mp := make(map[byte][]int)
	mp['L'] = []int{0, -1}
	mp['R'] = []int{0, 1}
	mp['U'] = []int{-1, 0}
	mp['D'] = []int{1, 0}
	ans := make([]int, m)
	for i := 0; i < m; i++ {
		x, y := startPos[0], startPos[1]
		t := 0
		for j := i; j < m; j++ {
			a, b := mp[s[j]][0], mp[s[j]][1]
			if 0 <= x+a && x+a < n && 0 <= y+b && y+b < n {
				x += a
				y += b
				t++
			} else {
				break
			}
		}
		ans[i] = t
	}
	return ans
}