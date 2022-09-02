func countSubarrays(nums []int) int64 {
	ans := 0
	i, n := 0, len(nums)
	for i < n {
		j := i + 1
		for j < n && nums[j] > nums[j-1] {
			j++
		}
		cnt := j - i
		ans += (1 + cnt) * cnt / 2
		i = j
	}
	return int64(ans)
}