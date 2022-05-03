func multiply(num1 string, num2 string) string {
	m, n := len(num1), len(num2)
	res := make([]int, m+n)
	mul := func(b, i int) {
		for j, t := m-1, 0; j >= 0 || t > 0; i, j = i+1, j-1 {
			if j >= 0 {
				a := int(num1[j] - '0')
				t += a * b
			}
			res[i] += t % 10
			if res[i] >= 10 {
				res[i] %= 10
				res[i+1]++
			}
			t /= 10
		}
	}
	for i := 0; i < n; i++ {
		b := num2[n-1-i] - '0'
		mul(int(b), i)
	}
	var ans []byte
	for _, v := range res {
		ans = append(ans, byte(v+'0'))
	}
	for len(ans) > 1 && ans[len(ans)-1] == '0' {
		ans = ans[:len(ans)-1]
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return string(ans)
}