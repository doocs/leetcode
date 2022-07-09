func reverse(x int) int {
	slot := make([]int, 11)
	count := 0
	for x != 0 {
		n := x % 10
		slot[count] = n
		count++
		x /= 10
	}
	result := 0
	flag := true
	for i := 0; i < count; i++ {
		if flag && slot[i] == 0 {
			continue
		}
		flag = false
		result = 10*result + slot[i]
	}
	if result > math.MaxInt32 || result < math.MinInt32 {
		return 0
	}
	return result
}