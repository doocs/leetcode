func smallestNumber(num int64) int64 {
	if num == 0 {
		return 0
	}
	cnt := make([]int, 10)
	neg := num < 0
	if neg {
		num = -num
	}
	for num != 0 {
		cnt[num%10]++
		num /= 10
	}
	ans := 0
	if neg {
		for i := 9; i >= 0; i-- {
			for j := 0; j < cnt[i]; j++ {
				ans = ans*10 + i
			}
		}
		return -int64(ans)
	}
	if cnt[0] > 0 {
		for i := 1; i < 10; i++ {
			if cnt[i] > 0 {
				ans = ans*10 + i
				cnt[i]--
				break
			}
		}
	}
	for i := 0; i < 10; i++ {
		for j := 0; j < cnt[i]; j++ {
			ans = ans*10 + i
		}
	}
	return int64(ans)
}