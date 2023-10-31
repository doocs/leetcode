func minimumTime(nums1 []int, nums2 []int, x int) int {
	n := len(nums1)
	f := make([]int, n+1)
	type pair struct{ a, b int }
	nums := make([]pair, n)
	var s1, s2 int
	for i := range nums {
		s1 += nums1[i]
		s2 += nums2[i]
		nums[i] = pair{nums1[i], nums2[i]}
	}
	sort.Slice(nums, func(i, j int) bool { return nums[i].b < nums[j].b })
	for _, e := range nums {
		a, b := e.a, e.b
		for j := n; j > 0; j-- {
			f[j] = max(f[j], f[j-1]+a+b*j)
		}
	}
	for j := 0; j <= n; j++ {
		if s1+s2*j-f[j] <= x {
			return j
		}
	}
	return -1
}