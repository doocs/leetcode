func smallestFactorization(num int) int {
	if num < 2 {
		return num
	}
	ans, mul := 0, 1
	for i := 9; i >= 2; i-- {
		if num%i == 0 {
			for num%i == 0 {
				num /= i
				ans = mul*i + ans
				mul *= 10
			}
		}
	}
	if num < 2 && ans <= math.MaxInt32 {
		return ans
	}
	return 0
}