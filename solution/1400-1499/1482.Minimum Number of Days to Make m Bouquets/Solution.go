func minDays(bloomDay []int, m int, k int) int {
	if m*k > len(bloomDay) {
		return -1
	}
	mi, mx := 0, 1000000000
	for _, bd := range bloomDay {
		mi = min(mi, bd)
		mx = max(mx, bd)
	}
	left, right := mi, mx
	for left < right {
		mid := (left + right) >> 1
		if check(bloomDay, m, k, mid) {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}

func check(bloomDay []int, m, k, day int) bool {
	cnt, cur := 0, 0
	for _, bd := range bloomDay {
		if bd <= day {
			cur++
		} else {
			cur = 0
		}
		if cur == k {
			cnt++
			cur = 0
		}
	}
	return cnt >= m
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}