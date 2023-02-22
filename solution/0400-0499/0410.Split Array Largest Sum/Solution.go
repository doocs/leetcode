func splitArray(nums []int, k int) int {
	mx := -1
	for _, num := range nums {
		mx = max(mx, num)
	}
	left, right := mx, int(1e9)
	for left < right {
		mid := (left + right) >> 1
		if check(nums, k, mid) {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}

func check(nums []int, k, x int) bool {
	s, cnt := 0, 1
	for _, num := range nums {
		if s+num > x {
			cnt++
			s = num
		} else {
			s += num
		}
	}
	return cnt <= k
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
