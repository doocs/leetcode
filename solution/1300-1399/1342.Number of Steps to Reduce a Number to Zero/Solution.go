func numberOfSteps(num int) int {
	ans := 0
	for num != 0 {
		if (num & 1) == 1 {
			num--
		} else {
			num >>= 1
		}
		ans++
	}
	return ans
}