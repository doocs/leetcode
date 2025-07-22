func smallestEqual(nums []int) int {
	for i, x := range nums {
		if i%10 == x {
			return i
		}
	}
	return -1
}
