func majorityElement(nums []int) []int {
	var n1, n2 int
	m1, m2 := 0, 1
	for _, m := range nums {
		if m == m1 {
			n1++
		} else if m == m2 {
			n2++
		} else if n1 == 0 {
			m1, n1 = m, 1
		} else if n2 == 0 {
			m2, n2 = m, 1
		} else {
			n1, n2 = n1-1, n2-1
		}
	}
	n1, n2 = 0, 0
	for _, m := range nums {
		if m == m1 {
			n1++
		} else if m == m2 {
			n2++
		}
	}
	var ans []int
	if n1 > len(nums)/3 {
		ans = append(ans, m1)
	}
	if n2 > len(nums)/3 {
		ans = append(ans, m2)
	}
	return ans
}