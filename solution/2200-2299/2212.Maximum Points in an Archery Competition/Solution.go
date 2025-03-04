func maximumBobPoints(numArrows int, aliceArrows []int) []int {
	st, mx := 0, 0
	m := len(aliceArrows)
	for mask := 1; mask < 1<<m; mask++ {
		cnt, s := 0, 0
		for i, x := range aliceArrows {
			if mask>>i&1 == 1 {
				s += i
				cnt += x + 1
			}
		}
		if cnt <= numArrows && s > mx {
			mx = s
			st = mask
		}
	}
	ans := make([]int, m)
	for i, x := range aliceArrows {
		if (st>>i)&1 == 1 {
			ans[i] = x + 1
			numArrows -= ans[i]
		}
	}
	ans[0] += numArrows
	return ans
}
