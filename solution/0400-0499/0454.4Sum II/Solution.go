func fourSumCount(nums1 []int, nums2 []int, nums3 []int, nums4 []int) (ans int) {
	cnt := map[int]int{}
	for _, a := range nums1 {
		for _, b := range nums2 {
			cnt[a+b]++
		}
	}
	for _, c := range nums3 {
		for _, d := range nums4 {
			ans += cnt[-(c + d)]
		}
	}
	return
}