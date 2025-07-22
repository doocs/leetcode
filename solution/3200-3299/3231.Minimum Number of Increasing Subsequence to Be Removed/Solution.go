func minOperations(nums []int) int {
	g := []int{}
	for _, x := range nums {
		l, r := 0, len(g)
		for l < r {
			mid := (l + r) >> 1
			if g[mid] < x {
				r = mid
			} else {
				l = mid + 1
			}
		}
		if l == len(g) {
			g = append(g, x)
		} else {
			g[l] = x
		}
	}
	return len(g)
}