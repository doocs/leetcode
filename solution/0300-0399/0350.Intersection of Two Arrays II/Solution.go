func intersect(nums1 []int, nums2 []int) (ans []int) {
	cnt := map[int]int{}
	for _, x := range nums1 {
		cnt[x]++
	}
	for _, x := range nums2 {
		if cnt[x] > 0 {
			ans = append(ans, x)
			cnt[x]--
		}
	}
	return
}