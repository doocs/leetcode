func maximumTastiness(price []int, k int) int {
	sort.Ints(price)
	check := func(x int) bool {
		s := price[0]
		cnt := 1
		for _, p := range price[1:] {
			if p-s >= x {
				s = p
				cnt++
			}
		}
		return cnt >= k
	}
	left, right := 0, 1000000000
	for left < right {
		mid := (left + right + 1) >> 1
		if check(mid) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}