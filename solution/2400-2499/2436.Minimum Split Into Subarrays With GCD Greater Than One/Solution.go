func minimumSplits(nums []int) int {
	ans, x := 1, nums[0]
	for _, v := range nums {
		x = gcd(x, v)
		if x == 1 {
			x = v
			ans++
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