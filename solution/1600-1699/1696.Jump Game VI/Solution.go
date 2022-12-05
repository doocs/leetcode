func maxResult(nums []int, k int) int {
	n := len(nums)
	f := make([]int, n)
	q := []int{0}
	for i, v := range nums {
		if i-q[0] > k {
			q = q[1:]
		}
		f[i] = v + f[q[0]]
		for len(q) > 0 && f[q[len(q)-1]] <= f[i] {
			q = q[:len(q)-1]
		}
		q = append(q, i)
	}
	return f[n-1]
}