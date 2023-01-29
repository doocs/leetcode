func addStrings(num1 string, num2 string) string {
	i, j := len(num1)-1, len(num2)-1
	ans := []byte{}
	for c := 0; i >= 0 || j >= 0 || c > 0; i, j = i-1, j-1 {
		if i >= 0 {
			c += int(num1[i] - '0')
		}
		if j >= 0 {
			c += int(num2[j] - '0')
		}
		ans = append(ans, byte(c%10+'0'))
		c /= 10
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return string(ans)
}

func subStrings(num1 string, num2 string) string {
	m, n := len(num1), len(num2)
	neg := m < n || (m == n && num1 < num2)
	if neg {
		num1, num2 = num2, num1
	}
	i, j := len(num1)-1, len(num2)-1
	ans := []byte{}
	for c := 0; i >= 0; i, j = i-1, j-1 {
		c = int(num1[i]-'0') - c
		if j >= 0 {
			c -= int(num2[j] - '0')
		}
		ans = append(ans, byte((c+10)%10+'0'))
		if c < 0 {
			c = 1
		} else {
			c = 0
		}
	}
	for len(ans) > 1 && ans[len(ans)-1] == '0' {
		ans = ans[:len(ans)-1]
	}
	if neg {
		ans = append(ans, '-')
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return string(ans)
}