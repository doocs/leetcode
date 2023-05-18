func minPatches(nums []int, n int) (ans int) {
	x := 1
	for i := 0; x <= n; {
		if i < len(nums) && nums[i] <= x {
			x += nums[i]
			i++
		} else {
			ans++
			x <<= 1
		}
	}
	return
}