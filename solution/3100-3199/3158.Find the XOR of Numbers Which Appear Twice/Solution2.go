func duplicateNumbersXOR(nums []int) (ans int) {
	mask := 0
	for _, x := range nums {
		if mask>>x&1 == 1 {
			ans ^= x
		} else {
			mask |= 1 << x
		}
	}
	return
}
