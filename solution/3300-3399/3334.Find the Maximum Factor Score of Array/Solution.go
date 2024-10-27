func maxScore(nums []int) int64 {
	n := len(nums)
	sufGcd := make([]int64, n+1)
	sufLcm := make([]int64, n+1)
	sufLcm[n] = 1
	for i := n - 1; i >= 0; i-- {
		sufGcd[i] = gcd(sufGcd[i+1], int64(nums[i]))
		sufLcm[i] = lcm(sufLcm[i+1], int64(nums[i]))
	}

	ans := sufGcd[0] * sufLcm[0]
	preGcd, preLcm := int64(0), int64(1)
	for i := 0; i < n; i++ {
		ans = max(ans, gcd(preGcd, sufGcd[i+1])*lcm(preLcm, sufLcm[i+1]))
		preGcd = gcd(preGcd, int64(nums[i]))
		preLcm = lcm(preLcm, int64(nums[i]))
	}
	return ans
}

func gcd(a, b int64) int64 {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}

func lcm(a, b int64) int64 {
	return a / gcd(a, b) * b
}
