func minKBitFlips(nums []int, k int) (ans int) {
	flipped := 0
	for i, x := range nums {
		if i >= k && nums[i-k] == -1 {
			flipped ^= 1
		}
		if flipped == x {
			if i+k > len(nums) {
				return -1
			}
			flipped ^= 1
			ans++
			nums[i] = -1
		}
	}
	return
}
