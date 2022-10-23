func subarrayGCD(nums []int, k int) int {
	ans, n := 0, len(nums)
	for i, x := range nums {
		for j := i; j < n; j++ {
			x = gcd(x, nums[j])
			if x == k {
				ans++
			}
		}
	}
	return ans
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}