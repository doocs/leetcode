func minOperations(nums1 []int, nums2 []int) int {
	s1, s2 := sum(nums1), sum(nums2)
	if s1 == s2 {
		return 0
	}
	if s1 > s2 {
		return minOperations(nums2, nums1)
	}
	freq := make([]int, 6)
	for _, x := range nums1 {
		freq[6-x]++
	}
	for _, x := range nums2 {
		freq[x-1]++
	}
	diff := s2 - s1
	ans := 0
	for i := 5; i > 0 && diff > 0; i-- {
		for freq[i] > 0 && diff > 0 {
			diff -= i
			freq[i]--
			ans++
		}
	}
	if diff > 0 {
		return -1
	}
	return ans
}

func sum(nums []int) int {
	s := 0
	for _, x := range nums {
		s += x
	}
	return s
}