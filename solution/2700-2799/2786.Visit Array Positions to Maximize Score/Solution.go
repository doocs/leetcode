func maxScore(nums []int, x int) int64 {
	const inf int = 1 << 40
	f := [2]int{-inf, -inf}
	f[nums[0]&1] = nums[0]
	for _, v := range nums[1:] {
		f[v&1] = max(f[v&1], f[v&1^1]-x) + v
	}
	return int64(max(f[0], f[1]))
}