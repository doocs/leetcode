func subarrayLCM(nums []int, k int) (ans int) {
	for i := range nums {
		a := 1
		for _, b := range nums[i:] {
			if k%b != 0 {
				break
			}
			a = lcm(a, b)
			if a == k {
				ans++
			}
		}
	}
	return
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}

func lcm(a, b int) int {
	return a / gcd(a, b) * b
}
