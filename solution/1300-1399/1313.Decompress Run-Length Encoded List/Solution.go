func decompressRLElist(nums []int) []int {
	var res []int
	for i := 1; i < len(nums); i += 2 {
		for j := 0; j < nums[i-1]; j++ {
			res = append(res, nums[i])
		}
	}
	return res
}