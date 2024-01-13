func canPartitionKSubsets(nums []int, k int) bool {
	s := 0
	for _, x := range nums {
		s += x
	}
	if s%k != 0 {
		return false
	}
	s /= k
	sort.Ints(nums)
	n := len(nums)
	f := make([]bool, 1<<n)
	cur := make([]int, 1<<n)
	f[0] = true
	for i := 0; i < 1<<n; i++ {
		if !f[i] {
			continue
		}
		for j := 0; j < n; j++ {
			if cur[i]+nums[j] > s {
				break
			}
			if i>>j&1 == 0 {
				f[i|1<<j] = true
				cur[i|1<<j] = (cur[i] + nums[j]) % s
			}
		}
	}
	return f[(1<<n)-1]
}