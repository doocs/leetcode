func majorityElement(nums []int) int {
	cnt, m := 0, 0
	for _, v := range nums {
		if cnt == 0 {
			m, cnt = v, 1
		} else {
			if m == v {
				cnt++
			} else {
				cnt--
			}
		}
	}
	cnt = 0
	for _, v := range nums {
		if m == v {
			cnt++
		}
	}
	if cnt > len(nums)/2 {
		return m
	}
	return -1
}