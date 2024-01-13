func smallestDifference(a []int, b []int) int {
	sort.Ints(b)
	var ans int = 1e18
	for _, x := range a {
		i := sort.SearchInts(b, x)
		if i < len(b) {
			ans = min(ans, b[i]-x)
		}
		if i > 0 {
			ans = min(ans, x-b[i-1])
		}
	}
	return ans
}