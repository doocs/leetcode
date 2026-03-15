func minCost(nums1 []int, nums2 []int) int {
	cnt2 := map[int]int{}
	for _, x := range nums2 {
		cnt2[x]++
	}

	cnt1 := map[int]int{}
	for _, x := range nums1 {
		if cnt2[x] > 0 {
			cnt2[x]--
		} else {
			cnt1[x]++
		}
	}

	ans := 0

	for _, v := range cnt1 {
		if v%2 == 1 {
			return -1
		}
		ans += v / 2
	}

	for _, v := range cnt2 {
		if v%2 == 1 {
			return -1
		}
	}

	return ans
}
