func weightedSum(parent []int, nums []int) int64 {
	n := len(nums)

	g := make([][]int, n)

	for i := 1; i < n; i++ {
		g[parent[i]] = append(g[parent[i]], i)
	}

	var ans int64

	q := []int{0}

	d := 0

	for len(q) > 0 {
		d++

		nq := make([]int, 0)

		for _, i := range q {
			ans += int64(nums[i]) * int64(1-d)

			for _, son := range g[i] {
				nq = append(nq, son)
			}
		}

		q = nq
	}

	var sum int64
	for _, x := range nums {
		sum += int64(x)
	}

	ans += int64(d) * sum

	return ans
}
