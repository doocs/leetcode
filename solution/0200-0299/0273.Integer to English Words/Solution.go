func numberToWords(num int) string {
	if num == 0 {
		return "Zero"
	}

	lt20 := []string{
		"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
		"Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
		"Sixteen", "Seventeen", "Eighteen", "Nineteen",
	}
	tens := []string{
		"", "Ten", "Twenty", "Thirty", "Forty", "Fifty",
		"Sixty", "Seventy", "Eighty", "Ninety",
	}
	thousands := []string{"Billion", "Million", "Thousand", ""}

	var transfer func(int) string
	transfer = func(num int) string {
		if num == 0 {
			return ""
		}
		if num < 20 {
			return lt20[num]
		}
		if num < 100 {
			if num%10 == 0 {
				return tens[num/10]
			}
			return tens[num/10] + " " + transfer(num%10)
		}
		if num%100 == 0 {
			return lt20[num/100] + " Hundred"
		}
		return lt20[num/100] + " Hundred " + transfer(num%100)
	}

	res := ""
	for i, j := 1000000000, 0; i > 0; i, j = i/1000, j+1 {
		cur := num / i
		if cur == 0 {
			continue
		}
		if res != "" {
			res += " "
		}
		res += transfer(cur)
		if thousands[j] != "" {
			res += " " + thousands[j]
		}
		num %= i
	}
	return res
}
