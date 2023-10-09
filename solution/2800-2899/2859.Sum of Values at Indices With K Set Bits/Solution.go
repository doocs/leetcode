func sumIndicesWithKSetBits(nums []int, k int) (ans int) {
	for i, x := range nums {
		if bits.OnesCount(uint(i)) == k {
			ans += x
		}
	}
	return
}