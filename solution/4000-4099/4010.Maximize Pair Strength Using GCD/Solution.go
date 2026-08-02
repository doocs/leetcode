func maxPairStrength(nums []int) int64 {
	n := len(nums)
	var ans int64 = 0

	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			g := gcd(int64(nums[i]), int64(nums[j]))
			x := int64(nums[i]) * int64(nums[j]) / (g * g)
			ans = max(ans, x)
		}
	}

	return ans
}

func gcd(a, b int64) int64 {
	for b != 0 {
		a, b = b, a%b
	}
	return a
}
