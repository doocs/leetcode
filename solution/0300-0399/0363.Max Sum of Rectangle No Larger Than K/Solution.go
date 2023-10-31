func maxSumSubmatrix(matrix [][]int, k int) int {
	m, n := len(matrix), len(matrix[0])
	const inf = 1 << 30
	ans := -inf
	for i := 0; i < m; i++ {
		nums := make([]int, n)
		for j := i; j < m; j++ {
			for h := 0; h < n; h++ {
				nums[h] += matrix[j][h]
			}
			s := 0
			rbt := redblacktree.NewWithIntComparator()
			rbt.Put(0, nil)
			for _, x := range nums {
				s += x
				if y, ok := rbt.Ceiling(s - k); ok {
					ans = max(ans, s-y.Key.(int))
				}
				rbt.Put(s, nil)
			}
		}

	}
	return ans
}