func countIntersectingIntervals(intervals [][]int) int64 {
	n := len(intervals)
	starts := make([]int, n)
	ends := make([]int, n)
	for i, p := range intervals {
		starts[i] = p[0]
		ends[i] = p[1]
	}
	slices.Sort(starts)
	slices.Sort(ends)
	ans := int64(n) * int64(n-1) / 2
	i := 0
	for _, start := range starts {
		for i < n && ends[i] < start {
			i++
		}
		ans -= int64(i)
	}
	return ans
}
