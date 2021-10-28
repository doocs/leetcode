func nextGreaterElement(nums1 []int, nums2 []int) []int {
	var stk []int
	mp := make(map[int]int)
	for _, num := range nums2 {
		for len(stk) > 0 && stk[len(stk)-1] < num {
			mp[stk[len(stk)-1]] = num
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, num)
	}
	var ans []int
	for _, num := range nums1 {
		val, ok := mp[num]
		if !ok {
			val = -1
		}
		ans = append(ans, val)
	}
	return ans
}