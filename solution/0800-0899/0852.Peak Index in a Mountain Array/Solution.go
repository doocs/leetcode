func peakIndexInMountainArray(arr []int) int {
	n := len(arr)
	left, right := 1, n-2
	for left < right {
		mid := left + (right-left)/2
		if arr[mid] < arr[mid+1] {
			left = mid + 1
		} else {
			right = mid
		}
	}
	return right
}
