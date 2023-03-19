func checkArithmeticSubarrays(nums []int, l []int, r []int) (ans []bool) {
	check := func(nums []int, l, r int) bool {
		s := map[int]struct{}{}
		n := r - l + 1
		a1, an := 1<<30, -(1 << 30)
		for _, x := range nums[l : r+1] {
			s[x] = struct{}{}
			if a1 > x {
				a1 = x
			}
			if an < x {
				an = x
			}
		}
		if (an-a1)%(n-1) != 0 {
			return false
		}
		d := (an - a1) / (n - 1)
		for i := 1; i < n; i++ {
			if _, ok := s[a1+(i-1)*d]; !ok {
				return false
			}
		}
		return true
	}
	for i := range l {
		ans = append(ans, check(nums, l[i], r[i]))
	}
	return
}