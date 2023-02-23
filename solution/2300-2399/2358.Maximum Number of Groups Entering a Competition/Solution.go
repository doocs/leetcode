func maximumGroups(grades []int) int {
	n := len(grades)
	return sort.Search(n, func(k int) bool {
		k++
		return k*k+k > n*2
	})
}