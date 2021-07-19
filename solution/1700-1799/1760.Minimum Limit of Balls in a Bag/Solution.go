func minimumSize(nums []int, maxOperations int) int {
	left, right := 1, max(nums)
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

func max(nums []int) int {
	res := 0
	for _, num := range nums {
		if res < num {
			res = num
		}
	}
	return res
}