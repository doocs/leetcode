func validSubarraySplit(nums []int) int {
	n := len(nums)
	const inf int = 0x3f3f3f3f
	f := make([]int, n+1)
	for i := 0; i < n; i++ {
		f[i] = inf
	}
	for i := n - 1; i >= 0; i-- {
		for j := i; j < n; j++ {
			if gcd(nums[i], nums[j]) > 1 {
				f[i] = min(f[i], 1+f[j+1])
			}
		}
	}
	if f[0] < inf {
		return f[0]
	}
	return -1
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
