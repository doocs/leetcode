func numberOfPairs(nums1 []int, nums2 []int, k int) (ans int64) {
	cnt1 := map[int]int{}
	for _, x := range nums1 {
		if x%k == 0 {
			cnt1[x/k]++
		}
	}
	if len(cnt1) == 0 {
		return 0
	}
	cnt2 := map[int]int{}
	for _, x := range nums2 {
		cnt2[x]++
	}
	mx := 0
	for x := range cnt1 {
		mx = max(mx, x)
	}
	for x, v := range cnt2 {
		s := 0
		for y := x; y <= mx; y += x {
			s += cnt1[y]
		}
		ans += int64(s) * int64(v)
	}
	return
}