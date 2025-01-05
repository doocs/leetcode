func maxLength(nums []int) int {
	mx, ml := 0, 1
	for _, x := range nums {
		mx = max(mx, x)
		ml = lcm(ml, x)
	}
	maxP := ml * mx
	n := len(nums)
	ans := 0
	for i := 0; i < n; i++ {
		p, g, l := 1, 0, 1
		for j := i; j < n; j++ {
			p *= nums[j]
			g = gcd(g, nums[j])
			l = lcm(l, nums[j])
			if p == g*l {
				ans = max(ans, j-i+1)
			}
			if p > maxP {
				break
			}
		}
	}
	return ans
}

func gcd(a, b int) int {
	for b != 0 {
		a, b = b, a%b
	}
	return a
}

func lcm(a, b int) int {
	return a / gcd(a, b) * b
}
