func isStraight(nums []int) bool {
	m := make(map[int]struct{})
	mi, ma := 14, 0
	for _, num := range nums {
		if num == 0 {
			continue
		}
		if _, exist := m[num]; exist {
			return false
		}
		mi = min(mi, num)
		ma = max(ma, num)
		m[num] = struct{}{}
	}
	return ma-mi < 5
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}
