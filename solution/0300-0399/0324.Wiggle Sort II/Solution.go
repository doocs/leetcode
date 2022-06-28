func wiggleSort(nums []int) {
	n := len(nums)
	arr := make([]int, n)
	copy(arr, nums)
	sort.Ints(arr)
	i, j := (n-1)>>1, n-1
	for k := 0; k < n; k++ {
		if k%2 == 0 {
			nums[k] = arr[i]
			i--
		} else {
			nums[k] = arr[j]
			j--
		}
	}
}