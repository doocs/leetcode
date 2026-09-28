func maxSubarray(nums []int) int {
	mx := slices.Max(nums)

	cntS := make([]int, (mx<<1)|1)
	cntD := make([]int, mx+1)

	ans := 0
	l := 0

	for r, x := range nums {
		for cntS[x] > 0 || cntD[x] > 0 {
			y := nums[l]
			l++

			for i := l; i < r; i++ {
				z := nums[i]
				cntS[y+z]--

				d := y - z
				if d < 0 {
					d = -d
				}
				cntD[d]--
			}
		}

		for i := l; i < r; i++ {
			y := nums[i]
			cntS[x+y]++

			d := x - y
			if d < 0 {
				d = -d
			}
			cntD[d]++
		}

		ans = max(ans, r-l+1)
	}

	return ans
}
