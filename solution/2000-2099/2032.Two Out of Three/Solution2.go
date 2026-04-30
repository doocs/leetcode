func twoOutOfThree(nums1 []int, nums2 []int, nums3 []int) (ans []int) {
	mask := make(map[int]int)
	for i, nums := range [][]int{nums1, nums2, nums3} {
		for _, x := range nums {
			mask[x] |= 1 << i
		}
	}
	for x, m := range mask {
		if m&(m-1) != 0 {
			ans = append(ans, x)
		}
	}
	return
}
