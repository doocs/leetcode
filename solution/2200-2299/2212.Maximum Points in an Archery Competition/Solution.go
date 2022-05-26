func maximumBobPoints(numArrows int, aliceArrows []int) []int {
	n := len(aliceArrows)
	state, mx := 0, -1
	for mask := 1; mask < 1<<n; mask++ {
		cnt, points := 0, 0
		for i, alice := range aliceArrows {
			if (mask>>i)&1 == 1 {
				cnt += alice + 1
				points += i
			}
		}
		if cnt <= numArrows && mx < points {
			state = mask
			mx = points
		}
	}
	ans := make([]int, n)
	for i, alice := range aliceArrows {
		if (state>>i)&1 == 1 {
			ans[i] = alice + 1
			numArrows -= ans[i]
		}
	}
	ans[0] += numArrows
	return ans
}