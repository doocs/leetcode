func kthSmallestProduct(nums1 []int, nums2 []int, k int64) int64 {
	m := len(nums1)
	n := len(nums2)
	a := max(abs(nums1[0]), abs(nums1[m-1]))
	b := max(abs(nums2[0]), abs(nums2[n-1]))
	r := int64(a) * int64(b)
	l := -r

	count := func(p int64) int64 {
		var cnt int64
		for _, x := range nums1 {
			if x > 0 {
				l, r := 0, n
				for l < r {
					mid := (l + r) >> 1
					if int64(x)*int64(nums2[mid]) > p {
						r = mid
					} else {
						l = mid + 1
					}
				}
				cnt += int64(l)
			} else if x < 0 {
				l, r := 0, n
				for l < r {
					mid := (l + r) >> 1
					if int64(x)*int64(nums2[mid]) <= p {
						r = mid
					} else {
						l = mid + 1
					}
				}
				cnt += int64(n - l)
			} else if p >= 0 {
				cnt += int64(n)
			}
		}
		return cnt
	}

	for l < r {
		mid := (l + r) >> 1
		if count(mid) >= k {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}