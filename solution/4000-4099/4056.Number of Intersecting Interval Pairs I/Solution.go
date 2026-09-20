func countIntersectingIntervals(intervals [][]int) int {
	n := len(intervals)
	starts := make([]int, n)
	ends := make([]int, n)
	for i, p := range intervals {
		starts[i] = p[0]
		ends[i] = p[1]
	}
	slices.Sort(starts)
	slices.Sort(ends)
	ans := n * (n - 1) / 2
	i := 0
	for _, start := range starts {
		for i < n && ends[i] < start {
			i++
		}
		ans -= i
	}
	return ans
}
