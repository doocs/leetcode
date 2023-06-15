func maxDistance(position []int, m int) int {
	sort.Ints(position)
	left, right := 1, position[len(position)-1]
	check := func(f int) bool {
		prev, cnt := position[0], 1
		for _, curr := range position[1:] {
			if curr-prev >= f {
				prev = curr
				cnt++
			}
		}
		return cnt >= m
	}
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