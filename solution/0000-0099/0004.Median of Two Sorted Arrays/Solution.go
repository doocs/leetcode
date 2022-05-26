/*
 * Report by leetcode.com
 * Runtime: 16 ms, Memory Usage: 5.4 MB
 */
func findMedianSortedArrays(nums1 []int, nums2 []int) float64 {
	mathMax := func(a, b int) int {
		if a > b {
			return a
		}
		return b
	}
	mathMin := func(a, b int) int {
		if a < b {
			return a
		}
		return b
	}

	var len1, len2 int
	len1 = len(nums1)
	len2 = len(nums2)
	if len1 > len2 {
		var tmp []int = nums1
		nums1 = nums2
		nums2 = tmp
		var t int = len1
		len1 = len2
		len2 = t
	}

	var min, max int = 0, 0
	max = len1

	halfLen := (len1 + len2 + 1) / 2

	for min <= max {
		i := (min + max) / 2
		j := halfLen - i

		if i < max && nums2[j-1] > nums1[i] {
			min++
		} else if i > min && nums1[i-1] > nums2[j] {
			max--
		} else {
			var maxLeft int
			if i == 0 {
				maxLeft = nums2[j-1]
			} else if j == 0 {
				maxLeft = nums1[i-1]
			} else {
				maxLeft = mathMax(nums1[i-1], nums2[j-1])
			}
			if ((len1 + len2) & 1) == 1 {
				return float64(maxLeft)
			}
			var minRight int
			if i == len1 {
				minRight = nums2[j]
			} else if j == len2 {
				minRight = nums1[i]
			} else {
				minRight = mathMin(nums2[j], nums1[i])
			}
			return float64(maxLeft+minRight) / 2
		}
	}
	return 0
}
