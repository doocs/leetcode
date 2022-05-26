func majorityElement(nums []int) int {
	var cnt, candidate int
	for _, num := range nums {
		if cnt == 0 {
			candidate = num
		}
		if candidate == num {
			cnt++
		} else {
			cnt--
		}
	}
	cnt = 0
	for _, num := range nums {
		if candidate == num {
			cnt++
		}
	}
	if cnt > len(nums)/2 {
		return candidate
	}
	return -1
}