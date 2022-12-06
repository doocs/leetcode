func minOperations(nums1 []int, nums2 []int) (ans int) {
	s1, s2 := sum(nums1), sum(nums2)
	if s1 == s2 {
		return 0
	}
	if s1 > s2 {
		return minOperations(nums2, nums1)
	}
	d := s2 - s1
	cnt := [6]int{}
	for _, v := range nums1 {
		cnt[6-v]++
	}
	for _, v := range nums2 {
		cnt[v-1]++
	}
	for i := 5; i > 0; i-- {
		for cnt[i] > 0 && d > 0 {
			d -= i
			cnt[i]--
			ans++
		}
	}
	if d <= 0 {
		return
	}
	return -1
}

func sum(nums []int) (s int) {
	for _, v := range nums {
		s += v
	}
	return
}