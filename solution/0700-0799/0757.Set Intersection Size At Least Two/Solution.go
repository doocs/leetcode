func intersectionSizeTwo(intervals [][]int) int {
	sort.Slice(intervals, func(i, j int) bool {
		a, b := intervals[i], intervals[j]
		if a[1] == b[1] {
			return a[0] > b[0]
		}
		return a[1] < b[1]
	})
	ans := 0
	s, e := -1, -1
	for _, v := range intervals {
		a, b := v[0], v[1]
		if a <= s {
			continue
		}
		if a > e {
			ans += 2
			s, e = b-1, b
		} else {
			ans += 1
			s, e = e, b
		}
	}
	return ans
}