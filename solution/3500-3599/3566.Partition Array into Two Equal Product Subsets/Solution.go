func checkEqualPartitions(nums []int, target int64) bool {
	n := len(nums)
	for i := 0; i < 1<<n; i++ {
		x, y := int64(1), int64(1)
		for j, v := range nums {
			if i>>j&1 == 1 {
				x *= int64(v)
			} else {
				y *= int64(v)
			}
			if x > target || y > target {
				break
			}
		}
		if x == target && y == target {
			return true
		}
	}
	return false
}