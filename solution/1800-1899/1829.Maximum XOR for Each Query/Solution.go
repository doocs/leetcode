func getMaximumXor(nums []int, maximumBit int) (ans []int) {
	xs := 0
	for _, x := range nums {
		xs ^= x
	}
	for i := range nums {
		x := nums[len(nums)-i-1]
		k := 0
		for j := maximumBit - 1; j >= 0; j-- {
			if xs>>j&1 == 0 {
				k |= 1 << j
			}
		}
		ans = append(ans, k)
		xs ^= x
	}
	return
}