func minOperations(nums []int) (ans int) {
	for i, x := range nums {
		if x == 0 {
			if i+2 >= len(nums) {
				return -1
			}
			nums[i+1] ^= 1
			nums[i+2] ^= 1
			ans++
		}
	}
	return
}