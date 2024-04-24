func singleDivisorTriplet(nums []int) (ans int64) {
	cnt := [101]int{}
	for _, x := range nums {
		cnt[x]++
	}
	f := func(a, b int) int {
		if a%b == 0 {
			return 1
		}
		return 0
	}
	for a := 1; a <= 100; a++ {
		for b := 1; b <= 100; b++ {
			for c := 1; c <= 100; c++ {
				s := a + b + c
				t := f(s, a) + f(s, b) + f(s, c)
				if t == 1 {
					if a == b {
						ans += int64(cnt[a] * (cnt[a] - 1) * cnt[c])
					} else if a == c {
						ans += int64(cnt[a] * (cnt[a] - 1) * cnt[b])
					} else if b == c {
						ans += int64(cnt[b] * (cnt[b] - 1) * cnt[a])
					} else {
						ans += int64(cnt[a] * cnt[b] * cnt[c])
					}
				}
			}
		}
	}
	return
}