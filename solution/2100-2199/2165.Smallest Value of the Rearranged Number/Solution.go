func smallestNumber(num int64) (ans int64) {
	neg := num < 0
	num = max(num, -num)
	cnt := make([]int, 10)

	for num > 0 {
		cnt[num%10]++
		num /= 10
	}

	if neg {
		for i := 9; i >= 0; i-- {
			for cnt[i] > 0 {
				ans = ans*10 + int64(i)
				cnt[i]--
			}
		}
		return -ans
	}

	if cnt[0] > 0 {
		for i := 1; i < 10; i++ {
			if cnt[i] > 0 {
				cnt[i]--
				ans = int64(i)
				break
			}
		}
	}

	for i := 0; i < 10; i++ {
		for cnt[i] > 0 {
			ans = ans*10 + int64(i)
			cnt[i]--
		}
	}

	return ans
}
