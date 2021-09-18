func asteroidCollision(asteroids []int) []int {
	var ans []int
	for _, a := range asteroids {
		if a > 0 {
			ans = append(ans, a)
		} else {
			for len(ans) > 0 && ans[len(ans)-1] > 0 && ans[len(ans)-1] < -a {
				ans = ans[:len(ans)-1]
			}
			if len(ans) > 0 && ans[len(ans)-1] == -a {
				ans = ans[:len(ans)-1]
			} else if len(ans) == 0 || ans[len(ans)-1] < -a {
				ans = append(ans, a)
			}
		}
	}
	return ans
}
