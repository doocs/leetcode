func decompressRLElist(nums []int) (ans []int) {
	for i := 1; i < len(nums); i += 2 {
		for j := 0; j < nums[i-1]; j++ {
			ans = append(ans, nums[i])
		}
	}
	return
}
