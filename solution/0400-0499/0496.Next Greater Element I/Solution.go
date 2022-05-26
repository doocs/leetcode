func nextGreaterElement(nums1 []int, nums2 []int) []int {
	stk := []int{}
	m := map[int]int{}
	for _, v := range nums2 {
		for len(stk) > 0 && stk[len(stk)-1] < v {
			m[stk[len(stk)-1]] = v
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, v)
	}
	var ans []int
	for _, v := range nums1 {
		val, ok := m[v]
		if !ok {
			val = -1
		}
		ans = append(ans, val)
	}
	return ans
}