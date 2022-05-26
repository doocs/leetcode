func maximum69Number(num int) int {
	s := strconv.Itoa(num)
	nums := []byte(s)
	for i, ch := range nums {
		if ch == '6' {
			nums[i] = '9'
			break
		}
	}
	ans, _ := strconv.Atoi(string(nums))
	return ans
}