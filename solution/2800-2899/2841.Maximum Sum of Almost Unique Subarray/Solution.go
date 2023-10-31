func maxSum(nums []int, m int, k int) int64 {
	cnt := map[int]int{}
	var s int64
	for _, x := range nums[:k] {
		cnt[x]++
		s += int64(x)
	}
	var ans int64
	if len(cnt) >= m {
		ans = s
	}
	for i := k; i < len(nums); i++ {
		cnt[nums[i]]++
		cnt[nums[i-k]]--
		if cnt[nums[i-k]] == 0 {
			delete(cnt, nums[i-k])
		}
		s += int64(nums[i]) - int64(nums[i-k])
		if len(cnt) >= m {
			ans = max(ans, s)
		}
	}
	return ans
}