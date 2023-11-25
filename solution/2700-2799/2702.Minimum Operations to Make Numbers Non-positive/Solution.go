func minOperations(nums []int, x int, y int) int {
	check := func(t int) bool {
		cnt := 0
		for _, v := range nums {
			if v > t*y {
				cnt += (v - t*y + x - y - 1) / (x - y)
			}
		}
		return cnt <= t
	}

	l, r := 0, slices.Max(nums)
	for l < r {
		mid := (l + r) >> 1
		if check(mid) {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}