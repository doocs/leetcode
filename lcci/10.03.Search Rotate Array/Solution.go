func search(arr []int, target int) int {
	l, r := 0, len(arr)-1
	for arr[l] == arr[r] {
		r--
	}
	for l < r {
		mid := (l + r) >> 1
		if arr[mid] > arr[r] {
			if arr[l] <= target && target <= arr[mid] {
				r = mid
			} else {
				l = mid + 1
			}
		} else if arr[mid] < arr[r] {
			if arr[mid] < target && target <= arr[r] {
				l = mid + 1
			} else {
				r = mid
			}
		} else {
			r--
		}
	}
	if arr[l] == target {
		return l
	}
	return -1
}