func nextGreaterElement(nums1 []int, nums2 []int) []int {
	stk := []int{}
	m := map[int]int{}
	for i := len(nums2) - 1; i >= 0; i-- {
		for len(stk) > 0 && stk[len(stk)-1] <= nums2[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			m[nums2[i]] = stk[len(stk)-1]
		}
		stk = append(stk, nums2[i])
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