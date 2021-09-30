func nextGreaterElement(nums1 []int, nums2 []int) []int {
	var stk []int
	mp := make(map[int]int)
	for i := len(nums2) - 1; i >= 0; i-- {
		for len(stk) > 0 && stk[len(stk)-1] <= nums2[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			mp[nums2[i]] = stk[len(stk)-1]
		} else {
			mp[nums2[i]] = -1
		}
		stk = append(stk, nums2[i])
	}
	var res []int
	for _, num := range nums1 {
		res = append(res, mp[num])
	}
	return res
}