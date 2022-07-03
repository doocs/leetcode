func largestInteger(num int) int {
	cnt := make([]int, 10)
	x := num
	for x != 0 {
		cnt[x%10]++
		x /= 10
	}
	x = num
	ans, t := 0, 1
	for x != 0 {
		v := x % 10
		x /= 10
		for y := 0; y < 10; y++ {
			if ((v^y)&1) == 0 && cnt[y] > 0 {
				cnt[y]--
				ans += y * t
				t *= 10
				break
			}
		}
	}
	return ans
}