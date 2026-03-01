func makeParityAlternating(nums []int) []int {
	if len(nums) == 1 {
		return []int{0, 0}
	}

	mn := slices.Min(nums)
	mx := slices.Max(nums)

	f := func(k int) []int {
		cnt := 0
		a, b := math.MaxInt, math.MinInt

		for i, x := range nums {
			if ((x - i) & 1) != k {
				cnt++
				if x == mn {
					x++
				} else if x == mx {
					x--
				}
			}
			a = min(a, x)
			b = max(b, x)
		}

		return []int{cnt, max(1, b-a)}
	}

	r0 := f(0)
	r1 := f(1)

	if r0[0] != r1[0] {
		if r0[0] < r1[0] {
			return r0
		}
		return r1
	}
	if r0[1] <= r1[1] {
		return r0
	}
	return r1
}
