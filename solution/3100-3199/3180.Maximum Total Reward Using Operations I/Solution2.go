func maxTotalReward(rewardValues []int) int {
	slices.Sort(rewardValues)
	nums := slices.Compact(rewardValues)
	n := len(nums)
	m := nums[n-1] << 1
	f := make([]bool, m)
	f[0] = true
	for _, v := range nums {
		for j := 1; j < m; j++ {
			if 0 <= j-v && j-v < v {
				f[j] = f[j] || f[j-v]
			}
		}
	}
	ans := m - 1
	for !f[ans] {
		ans--
	}
	return ans
}