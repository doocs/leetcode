func tripletCount(a []int, b []int, c []int) (ans int) {
	cnt1 := [2]int{}
	cnt2 := [2]int{}
	cnt3 := [2]int{}
	for _, x := range a {
		cnt1[bits.OnesCount(uint(x))%2]++
	}
	for _, x := range b {
		cnt2[bits.OnesCount(uint(x))%2]++
	}
	for _, x := range c {
		cnt3[bits.OnesCount(uint(x))%2]++
	}
	for i := 0; i < 2; i++ {
		for j := 0; j < 2; j++ {
			for k := 0; k < 2; k++ {
				if (i+j+k)%2 == 0 {
					ans += cnt1[i] * cnt2[j] * cnt3[k]
				}
			}
		}
	}
	return
}