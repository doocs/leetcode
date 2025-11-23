func totalWaviness(num1 int, num2 int) (ans int) {
	for x := num1; x <= num2; x++ {
		ans += f(x)
	}
	return
}

func f(x int) int {
	nums := make([]int, 0, 20)
	for x > 0 {
		nums = append(nums, x%10)
		x /= 10
	}
	m := len(nums)
	if m < 3 {
		return 0
	}
	s := 0
	for i := 1; i < m-1; i++ {
		if (nums[i] > nums[i-1] && nums[i] > nums[i+1]) ||
			(nums[i] < nums[i-1] && nums[i] < nums[i+1]) {
			s++
		}
	}
	return s
}
