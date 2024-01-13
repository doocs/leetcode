func combinationSum3(k int, n int) (ans [][]int) {
	for mask := 0; mask < 1<<9; mask++ {
		if bits.OnesCount(uint(mask)) == k {
			t := []int{}
			s := 0
			for i := 0; i < 9; i++ {
				if mask>>i&1 == 1 {
					s += i + 1
					t = append(t, i+1)
				}
			}
			if s == n {
				ans = append(ans, t)
			}
		}
	}
	return
}