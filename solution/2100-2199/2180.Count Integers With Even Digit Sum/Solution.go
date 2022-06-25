func countEven(num int) int {
	ans := 0
	for i := 1; i <= num; i++ {
		t := 0
		for j := i; j > 0; j /= 10 {
			t += j % 10
		}
		if t%2 == 0 {
			ans++
		}
	}
	return ans
}