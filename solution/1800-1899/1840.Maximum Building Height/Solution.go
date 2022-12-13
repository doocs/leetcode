func maxBuilding(n int, restrictions [][]int) (ans int) {
	r := restrictions
	r = append(r, []int{1, 0})
	sort.Slice(r, func(i, j int) bool { return r[i][0] < r[j][0] })
	if r[len(r)-1][0] != n {
		r = append(r, []int{n, n - 1})
	}
	m := len(r)
	for i := 1; i < m; i++ {
		r[i][1] = min(r[i][1], r[i-1][1]+r[i][0]-r[i-1][0])
	}
	for i := m - 2; i > 0; i-- {
		r[i][1] = min(r[i][1], r[i+1][1]+r[i+1][0]-r[i][0])
	}
	for i := 0; i < m-1; i++ {
		t := (r[i][1] + r[i+1][1] + r[i+1][0] - r[i][0]) / 2
		ans = max(ans, t)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}