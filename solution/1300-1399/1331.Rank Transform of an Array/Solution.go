func arrayRankTransform(arr []int) []int {
	s := make(map[int]bool)
	for _, v := range arr {
		s[v] = true
	}
	var alls []int
	for v := range s {
		alls = append(alls, v)
	}
	sort.Ints(alls)
	m := make(map[int]int)
	for i, v := range alls {
		m[v] = i + 1
	}
	var ans []int
	for _, v := range arr {
		ans = append(ans, m[v])
	}
	return ans
}