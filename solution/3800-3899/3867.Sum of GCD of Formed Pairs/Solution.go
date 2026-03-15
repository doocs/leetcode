func gcdSum(nums []int) int64 {
	n := len(nums)
	prefixGcd := make([]int, n)
	mx := 0

	for i, x := range nums {
		if x > mx {
			mx = x
		}
		prefixGcd[i] = gcd(x, mx)
	}

	sort.Ints(prefixGcd)

	var ans int64
	for i := 0; i < n/2; i++ {
		ans += int64(gcd(prefixGcd[i], prefixGcd[n-i-1]))
	}

	return ans
}

func gcd(a, b int) int {
	for b != 0 {
		a, b = b, a%b
	}
	return a
}
