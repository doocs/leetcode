func maxDistance(position []int, m int) int {
	sort.Ints(position)
	left, right := 1, position[len(position)-1]
	for left < right {
		mid := (left + right + 1) >> 1
		if check(position, mid, m) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}

func check(position []int, f, m int) bool {
	pre, cnt := position[0], 1
	for i := 1; i < len(position); i++ {
		if position[i]-pre >= f {
			cnt++
			pre = position[i]
		}
	}
	return cnt >= m
}