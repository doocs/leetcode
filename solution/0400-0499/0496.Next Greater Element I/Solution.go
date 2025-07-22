func nextGreaterElement(nums1 []int, nums2 []int) (ans []int) {
	stk := []int{}
	d := map[int]int{}
	for i := len(nums2) - 1; i >= 0; i-- {
		x := nums2[i]
		for len(stk) > 0 && stk[len(stk)-1] < x {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			d[x] = stk[len(stk)-1]
		}
		stk = append(stk, x)
	}
	for _, x := range nums1 {
		if v, ok := d[x]; ok {
			ans = append(ans, v)
		} else {
			ans = append(ans, -1)
		}
	}
	return
}
