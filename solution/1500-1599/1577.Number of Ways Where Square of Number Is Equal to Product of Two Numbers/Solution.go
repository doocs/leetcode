func numTriplets(nums1 []int, nums2 []int) (ans int) {
	cnt1 := map[int]int{}
	cnt2 := map[int]int{}
	for _, v := range nums1 {
		cnt1[v]++
	}
	for _, v := range nums2 {
		cnt2[v]++
	}
	for a, x := range cnt1 {
		for b, y := range cnt2 {
			if a*a%b == 0 {
				c := a * a / b
				if b == c {
					ans += x * y * (y - 1)
				} else {
					ans += x * y * cnt2[c]
				}
			}
			if b*b%a == 0 {
				c := b * b / a
				if a == c {
					ans += x * (x - 1) * y
				} else {
					ans += x * y * cnt1[c]
				}
			}
		}
	}
	ans /= 2
	return
}