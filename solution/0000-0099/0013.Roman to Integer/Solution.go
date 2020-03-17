// 字符          数值
// I             1
// V             5
// X             10
// L             50
// C             100
// D             500
// M             1000
var table = make(map[string]int)

func init() {
	table["I"] = 1
	table["IV"] = 4
	table["V"] = 5
	table["IX"] = 9
	table["X"] = 10
	table["XL"] = 40
	table["L"] = 50
	table["XC"] = 90
	table["C"] = 100
	table["CD"] = 400
	table["D"] = 500
	table["CM"] = 900
	table["M"] = 1000
}

func romanToInt(s string) int {
	var result int
	lenS := len(s)
	for i:=0; i<lenS; i++ {
		if i < lenS - 1 {
			if v, exist := table[s[i:i+2]]; exist {
				result += v
				i++
				continue
			}
		}
        result += table[string(s[i])]
	}
	return result
}