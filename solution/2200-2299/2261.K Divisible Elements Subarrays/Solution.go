func countDistinct(nums []int, k int, p int) int {
	s := map[int]bool{}
	base1, base2 := 131, 13331
	mod1, mod2 := 1000000007, 1000000009
	for i := range nums {
		h1, h2, cnt := 0, 0, 0
		for j := i; j < len(nums); j++ {
			if nums[j]%p == 0 {
				cnt++
				if cnt > k {
					break
				}
			}
			h1 = (h1*base1 + nums[j]) % mod1
			h2 = (h2*base2 + nums[j]) % mod2
			s[h1<<32|h2] = true
		}
	}
	return len(s)
}
