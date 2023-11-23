func maxGcdSum(nums []int, k int) int64 {
	n := len(nums)
	s := make([]int64, n+1)
	s[0] = 0
	for i, x := range nums {
		s[i+1] = s[i] + int64(x)
	}
	type pair [2]int
	var f []pair
	var ans int64
	for i := 0; i < n; i++ {
		var g []pair
		for _, p := range f {
			j, x := p[0], p[1]
			y := int(gcd(int(x), nums[i]))
			if len(g) == 0 || g[len(g)-1][1] != y {
				g = append(g, pair{j, y})
			}
		}
		f = g
		f = append(f, pair{i, nums[i]})
		for _, p := range f {
			j, x := p[0], p[1]
			if i-j+1 >= k {
				ans = max(ans, (s[i+1]-s[j])*int64(x))
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