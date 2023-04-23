func minOperations(nums []int) int {
	n := len(nums)
	cnt := 0
	for _, x := range nums {
		if x == 1 {
			cnt++
		}
	}
	if cnt > 0 {
		return n - cnt
	}
	mi := n + 1
	for i := 0; i < n; i++ {
		g := 0
		for j := i; j < n; j++ {
			g = gcd(g, nums[j])
			if g == 1 {
				mi = min(mi, j-i+1)
			}
		}
	}
	if mi > n {
		return -1
	}
	return n - 1 + mi - 1
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}