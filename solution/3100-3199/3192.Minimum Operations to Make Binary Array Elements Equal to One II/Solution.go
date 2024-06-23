func minOperations(nums []int) (ans int) {
	v := 0
	for _, x := range nums {
		x ^= v
		if x == 0 {
			v ^= 1
			ans++
		}
	}
	return
}