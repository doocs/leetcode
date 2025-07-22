func numTriplets(nums1 []int, nums2 []int) int {
	cnt1 := count(nums1)
	cnt2 := count(nums2)
	return cal(cnt1, nums2) + cal(cnt2, nums1)
}

func count(nums []int) map[int]int {
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	return cnt
}

func cal(cnt map[int]int, nums []int) (ans int) {
	for _, x := range nums {
		for y, v1 := range cnt {
			z := x * x / y
			if y*z == x*x {
				if v2, ok := cnt[z]; ok {
					if y == z {
						v2--
					}
					ans += v1 * v2
				}
			}
		}
	}
	ans /= 2
	return
}
