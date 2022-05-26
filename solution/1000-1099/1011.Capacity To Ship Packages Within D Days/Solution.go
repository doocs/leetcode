func shipWithinDays(weights []int, days int) int {
	left, right := 1, 25000000
	for left < right {
		mid := (left + right) >> 1
		if check(weights, days, mid) {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}

func check(weights []int, days, capacity int) bool {
	cnt, t := 1, 0
	for _, w := range weights {
		if w > capacity {
			return false
		}
		if t+w <= capacity {
			t += w
		} else {
			cnt++
			t = w
		}
	}
	return cnt <= days
}