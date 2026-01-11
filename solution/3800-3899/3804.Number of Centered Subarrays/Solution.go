func centeredSubarrays(nums []int) int {
	n := len(nums)
	ans := 0
	for i := 0; i < n; i++ {
		st := make(map[int]struct{})
		s := 0
		for j := i; j < n; j++ {
			s += nums[j]
			st[nums[j]] = struct{}{}
			if _, ok := st[s]; ok {
				ans++
			}
		}
	}
	return ans
}
