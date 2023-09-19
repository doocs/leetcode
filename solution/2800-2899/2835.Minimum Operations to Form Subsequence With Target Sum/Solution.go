func minOperations(nums []int, target int) (ans int) {
	s := 0
	cnt := [32]int{}
	for _, x := range nums {
		s += x
		for i := 0; i < 32; i++ {
			if x>>i&1 > 0 {
				cnt[i]++
			}
		}
	}
	if s < target {
		return -1
	}
	var i, j int
	for {
		for i < 32 && target>>i&1 == 0 {
			i++
		}
		if i == 32 {
			return
		}
		for j < i {
			cnt[j+1] += cnt[j] >> 1
			cnt[j] %= 2
			j++
		}
		for cnt[j] == 0 {
			cnt[j] = 1
			j++
		}
		ans += j - i
		cnt[j]--
		j = i
		i++
	}
}