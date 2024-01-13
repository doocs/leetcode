func minimumOperations(nums []int, start int, goal int) int {
	next := func(x int) []int {
		var res []int
		for _, num := range nums {
			res = append(res, []int{x + num, x - num, x ^ num}...)
		}
		return res
	}
	q := []int{start}
	vis := make([]bool, 1001)
	ans := 0
	for len(q) > 0 {
		ans++
		for n := len(q); n > 0; n-- {
			x := q[0]
			q = q[1:]
			for _, y := range next(x) {
				if y == goal {
					return ans
				}
				if y >= 0 && y <= 1000 && !vis[y] {
					vis[y] = true
					q = append(q, y)
				}
			}
		}
	}
	return -1
}