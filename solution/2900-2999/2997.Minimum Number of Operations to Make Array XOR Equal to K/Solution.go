func minOperations(nums []int, k int) (ans int) {
	for _, x := range nums {
		k ^= x
	}
	return bits.OnesCount(uint(k))
}