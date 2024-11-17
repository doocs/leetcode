func isZeroArray(nums []int, queries [][]int) bool {
	d := make([]int, len(nums)+1)
	for _, q := range queries {
		l, r := q[0], q[1]
		d[l]++
		d[r+1]--
	}
	s := 0
	for i, x := range nums {
		s += d[i]
		if x > s {
			return false
		}
	}
	return true
}
