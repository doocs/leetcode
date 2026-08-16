func minOperations(s string) int {
	n := len(s)
	ans := int(^uint(0) >> 1)

	for k := 0; k < n; k++ {
		t := k
		i, j := 0, n-1

		for i < j {
			x := int(s[(i+k)%n] - 'a')
			y := int(s[(j+k)%n] - 'a')

			d := abs(x - y)
			t += min(d, 26-d)

			i++
			j--
		}

		ans = min(ans, t)
	}

	return ans
}

func abs(x int) int {
	return max(x, -x)
}
