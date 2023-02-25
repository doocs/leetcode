func minimizedMaximum(n int, quantities []int) int {
	return 1 + sort.Search(1e5, func(x int) bool {
		x++
		cnt := 0
		for _, v := range quantities {
			cnt += (v + x - 1) / x
		}
		return cnt <= n
	})
}