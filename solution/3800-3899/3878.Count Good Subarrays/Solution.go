func countGoodSubarrays(nums []int) int64 {
	n := len(nums)

	l := make([]int, n)
	for i := range l {
		l[i] = -1
	}
	stk := []int{}

	for i, x := range nums {
		for len(stk) > 0 && nums[stk[len(stk)-1]] < x &&
			(nums[stk[len(stk)-1]]|x) == x {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			l[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}

	r := make([]int, n)
	for i := range r {
		r[i] = n
	}
	stk = stk[:0]

	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 &&
			(nums[stk[len(stk)-1]]|nums[i]) == nums[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			r[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}

	var ans int64
	for i := 0; i < n; i++ {
		ans += int64(i-l[i]) * int64(r[i]-i)
	}
	return ans
}
