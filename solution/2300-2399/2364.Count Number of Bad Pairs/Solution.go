func countBadPairs(nums []int) int64 {
	n := len(nums)
	for i := range nums {
		nums[i] = i - nums[i]
	}
	cnt := map[int]int{}
	for _, v := range nums {
		cnt[v]++
	}
	ans := 0
	for _, v := range cnt {
		ans += v * (n - v)
	}
	ans >>= 1
	return int64(ans)
}