func smallerNumbersThanCurrent(nums []int) (ans []int) {
	arr := make([]int, len(nums))
	copy(arr, nums)
	sort.Ints(arr)
	for i, x := range nums {
		nums[i] = sort.SearchInts(arr, x)
	}
	return nums
}