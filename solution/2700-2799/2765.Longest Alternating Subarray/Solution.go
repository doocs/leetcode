func alternatingSubarray(nums []int) int {
	ans, n := -1, len(nums)
	for i := range nums {
		k := 1
		j := i
		for ; j+1 < n && nums[j+1]-nums[j] == k; j++ {
			k *= -1
		}
		if t := j - i + 1; t > 1 && ans < t {
			ans = t
		}
	}
	return ans
}