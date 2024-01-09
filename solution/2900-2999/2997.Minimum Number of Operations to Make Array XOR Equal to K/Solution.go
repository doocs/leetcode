func minOperations(nums []int, k int) (ans int) {
	for i := 0; i < 20; i++ {
		v := 0
		for _, x := range nums {
			v ^= x >> i & 1
		}
		ans += k>>i&1 ^ v
	}
	return
}