func sumFourDivisors(nums []int) (ans int) {
	f := func(x int) int {
		cnt, s := 2, x+1
		for i := 2; i <= x/i; i++ {
			if x%i == 0 {
				cnt++
				s += i
				if i*i != x {
					cnt++
					s += x / i
				}
			}
		}
		if cnt == 4 {
			return s
		}
		return 0
	}
	for _, x := range nums {
		ans += f(x)
	}
	return
}