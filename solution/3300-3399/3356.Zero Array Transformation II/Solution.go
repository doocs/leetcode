func minZeroArray(nums []int, queries [][]int) int {
	n, m := len(nums), len(queries)
	l := sort.Search(m+1, func(k int) bool {
		d := make([]int, n+1)
		for _, q := range queries[:k] {
			l, r, val := q[0], q[1], q[2]
			d[l] += val
			d[r+1] -= val
		}
		s := 0
		for i, x := range nums {
			s += d[i]
			if x > s {
				return false
			}
		}
		return true
	})
	if l > m {
		return -1
	}
	return l
}
