func partitionArray(nums []int, k int) int {
	sort.Ints(nums)
	d, ans := 0, 1
	for i, v := range nums[1:] {
		t := v - nums[i]
		if d+t <= k {
			d += t
		} else {
			d = 0
			ans++
		}
	}
	return ans
}