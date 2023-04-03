func minNumber(nums1 []int, nums2 []int) int {
	var mask1, mask2 uint
	for _, x := range nums1 {
		mask1 |= 1 << x
	}
	for _, x := range nums2 {
		mask2 |= 1 << x
	}
	if mask := mask1 & mask2; mask != 0 {
		return bits.TrailingZeros(mask)
	}
	a, b := bits.TrailingZeros(mask1), bits.TrailingZeros(mask2)
	return min(a*10+b, b*10+a)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}