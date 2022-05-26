func minimumSize(nums []int, maxOperations int) int {
	left, right := 1, int(1e9)
	for left < right {
		mid := (left + right) >> 1
		var ops int
		for _, num := range nums {
			ops += (num - 1) / mid
		}
		if ops <= maxOperations {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}