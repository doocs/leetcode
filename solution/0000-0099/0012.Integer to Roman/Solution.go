// 字符          数值
// I             1
// V             5
// X             10
// L             50
// C             100
// D             500
// M             1000
var table = make(map[int]string)
var keys = []int{1,4,5,9,10,40,50,90,100,400,500,900,1000}

func init() {
	table[1] = "I"
	table[4] = "IV"
	table[5] = "V"
	table[9] = "IX"
	table[10] = "X"
	table[40] = "XL"
	table[50] = "L"
	table[90] = "XC"
	table[100] = "C"
	table[400] = "CD"
	table[500] = "D"
	table[900] = "CM"
	table[1000] = "M"
}

func intToRoman(num int) string {
	if n, exist := table[num]; exist {
		return n
	}
	var result string
	lenKeys := len(keys)
	for i:=lenKeys-1; i>=0; i-- {
		d := keys[i]
		count := num/d
		result += genreateRoman(d, count)
		num = num - d * count
	}
	return result
}

func genreateRoman(n, count int) string {
	var r string
	for i:=0; i<count; i++ {
		r += table[n]
	}
	return r
}
