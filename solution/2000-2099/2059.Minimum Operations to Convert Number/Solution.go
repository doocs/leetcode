func minimumOperations(nums []int, start int, goal int) int {
	type pair struct {
		x    int
		step int
	}

	ops := []func(int, int) int{
		func(x, y int) int { return x + y },
		func(x, y int) int { return x - y },
		func(x, y int) int { return x ^ y },
	}
	vis := make([]bool, 1001)
	q := []pair{{start, 0}}

	for len(q) > 0 {
		x, step := q[0].x, q[0].step
		q = q[1:]
		for _, num := range nums {
			for _, op := range ops {
				nx := op(x, num)
				if nx == goal {
					return step + 1
				}
				if nx >= 0 && nx <= 1000 && !vis[nx] {
					q = append(q, pair{nx, step + 1})
					vis[nx] = true
				}
			}
		}
	}
	return -1
}
