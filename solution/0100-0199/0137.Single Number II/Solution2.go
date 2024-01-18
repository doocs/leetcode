func singleNumber(nums []int) int {
	a, b := 0, 0
	for _, c := range nums {
		aa := (^a & b & c) | (a & ^b & ^c)
		bb := ^a & (b ^ c)
		a, b = aa, bb
	}
	return b
}