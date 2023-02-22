func minOperations(n int) (ans int) {
	cnt := 0
	for ; n > 0; n >>= 1 {
		if n&1 == 1 {
			cnt++
		} else if cnt > 0 {
			ans++
			if cnt == 1 {
				cnt = 0
			} else {
				cnt = 1
			}
		}
	}
	if cnt == 1 {
		ans++
	} else if cnt > 1 {
		ans += 2
	}
	return
}