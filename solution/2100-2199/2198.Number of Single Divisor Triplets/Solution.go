func singleDivisorTriplet(nums []int) int64 {
	counter := make([]int, 101)
	for _, x := range nums {
		counter[x]++
	}
	var ans int64
	check := func(a, b, c int) bool {
		s := a + b + c
		cnt := 0
		if s%a == 0 {
			cnt++
		}
		if s%b == 0 {
			cnt++
		}
		if s%c == 0 {
			cnt++
		}
		return cnt == 1
	}
	for i := 1; i <= 100; i++ {
		for j := 1; j <= 100; j++ {
			for k := 1; k <= 100; k++ {
				if check(i, j, k) {
					cnt1, cnt2, cnt3 := counter[i], counter[j], counter[k]
					if i == j {
						ans += int64(cnt1 * (cnt1 - 1) * cnt3)
					} else if i == k {
						ans += int64(cnt1 * (cnt1 - 1) * cnt2)
					} else if j == k {
						ans += int64(cnt1 * cnt2 * (cnt2 - 1))
					} else {
						ans += int64(cnt1 * cnt2 * cnt3)
					}
				}
			}
		}
	}
	return ans
}