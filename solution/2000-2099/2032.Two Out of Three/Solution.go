func twoOutOfThree(nums1 []int, nums2 []int, nums3 []int) (ans []int) {
	get := func(nums []int) (s [101]int) {
		for _, v := range nums {
			s[v] = 1
		}
		return
	}
	s1, s2, s3 := get(nums1), get(nums2), get(nums3)
	for i := 1; i <= 100; i++ {
		if s1[i]+s2[i]+s3[i] > 1 {
			ans = append(ans, i)
		}
	}
	return
}