func resultArray(nums []int) []int {
	arr1 := []int{nums[0]}
	arr2 := []int{nums[1]}
	for _, x := range nums[2:] {
		if arr1[len(arr1)-1] > arr2[len(arr2)-1] {
			arr1 = append(arr1, x)
		} else {
			arr2 = append(arr2, x)
		}
	}
	return append(arr1, arr2...)
}