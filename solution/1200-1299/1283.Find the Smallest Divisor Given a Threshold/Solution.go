func smallestDivisor(nums []int, threshold int) int {
	return sort.Search(slices.Max(nums), func(v int) bool {
		v++
		s := 0
		for _, x := range nums {
			s += (x + v - 1) / v
		}
		return s <= threshold
	}) + 1
}