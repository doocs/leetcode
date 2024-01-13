func checkIfExist(arr []int) bool {
	cnt := 0
	for _, v := range arr {
		if v == 0 {
			cnt++
			if cnt > 1 {
				return true
			}
		}
	}
	sort.Ints(arr)
	n := len(arr)
	for _, v := range arr {
		if v != 0 {
			left, right := 0, n
			for left < right {
				mid := (left + right) >> 1
				if arr[mid] >= v*2 {
					right = mid
				} else {
					left = mid + 1
				}
			}
			if right != n && arr[left] == v*2 {
				return true
			}
		}
	}
	return false
}