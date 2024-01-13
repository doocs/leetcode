func maxDistance(nums1 []int, nums2 []int) int {
	m, n := len(nums1), len(nums2)
	ans := 0
	for i, j := 0, 0; i < m; i++ {
		for j < n && nums1[i] <= nums2[j] {
			j++
		}
		if ans < j-i-1 {
			ans = j - i - 1
		}
	}
	return ans
}