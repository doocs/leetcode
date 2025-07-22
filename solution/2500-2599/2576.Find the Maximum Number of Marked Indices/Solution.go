func maxNumOfMarkedIndices(nums []int) (ans int) {
	sort.Ints(nums)
	i, n := 0, len(nums)
	for _, x := range nums[(n+1)/2:] {
		if nums[i]*2 <= x {
			i++
		}
	}
	return i * 2
}
