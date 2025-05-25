func sumOfLargestPrimes(s string) (ans int64) {
	st := make(map[int64]struct{})
	n := len(s)

	for i := 0; i < n; i++ {
		var x int64 = 0
		for j := i; j < n; j++ {
			x = x*10 + int64(s[j]-'0')
			if isPrime(x) {
				st[x] = struct{}{}
			}
		}
	}

	nums := make([]int64, 0, len(st))
	for num := range st {
		nums = append(nums, num)
	}
	sort.Slice(nums, func(i, j int) bool { return nums[i] < nums[j] })
	for i := len(nums) - 1; i >= 0 && len(nums)-i <= 3; i-- {
		ans += nums[i]
	}
	return
}

func isPrime(x int64) bool {
	if x < 2 {
		return false
	}
	sqrtX := int64(math.Sqrt(float64(x)))
	for i := int64(2); i <= sqrtX; i++ {
		if x%i == 0 {
			return false
		}
	}
	return true
}
