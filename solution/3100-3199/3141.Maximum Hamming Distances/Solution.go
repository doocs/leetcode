func maxHammingDistances(nums []int, m int) []int {
	dist := make([]int, 1<<m)
	for i := range dist {
		dist[i] = -1
	}
	q := []int{}
	for _, x := range nums {
		dist[x] = 0
		q = append(q, x)
	}
	for k := 1; len(q) > 0; k++ {
		t := []int{}
		for _, x := range q {
			for i := 0; i < m; i++ {
				y := x ^ (1 << i)
				if dist[y] == -1 {
					dist[y] = k
					t = append(t, y)
				}
			}
		}
		q = t
	}
	for i, x := range nums {
		nums[i] = m - dist[x^(1<<m-1)]
	}
	return nums
}