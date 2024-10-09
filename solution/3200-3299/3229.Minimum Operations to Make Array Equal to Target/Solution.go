func minimumOperations(nums []int, target []int) int64 {
	f := abs(target[0] - nums[0])
	for i := 1; i < len(target); i++ {
		x := target[i] - nums[i]
		y := target[i-1] - nums[i-1]
		if x*y > 0 {
			if d := abs(x) - abs(y); d > 0 {
				f += d
			}
		} else {
			f += abs(x)
		}
	}
	return int64(f)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}