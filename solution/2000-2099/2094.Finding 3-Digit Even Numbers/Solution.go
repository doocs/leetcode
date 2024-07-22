func findEvenNumbers(digits []int) (ans []int) {
	cnt := [10]int{}
	for _, x := range digits {
		cnt[x]++
	}
	for x := 100; x < 1000; x += 2 {
		cnt1 := [10]int{}
		for y := x; y > 0; y /= 10 {
			cnt1[y%10]++
		}
		ok := true
		for i := 0; i < 10 && ok; i++ {
			ok = cnt[i] >= cnt1[i]
		}
		if ok {
			ans = append(ans, x)
		}
	}
	return
}