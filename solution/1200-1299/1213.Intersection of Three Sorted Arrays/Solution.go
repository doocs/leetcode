func arraysIntersection(arr1 []int, arr2 []int, arr3 []int) []int {
	var res []int
	for _, num := range arr1 {
		if find(arr2, num) && find(arr3, num) {
			res = append(res, num)
		}
	}
	return res
}

func find(arr []int, val int) bool {
	left, right := 0, len(arr)-1
	for left < right {
		mid := (left + right) >> 1
		if arr[mid] >= val {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return arr[left] == val
}