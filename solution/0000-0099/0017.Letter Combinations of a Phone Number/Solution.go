var table = map[string][]string{
	"2": {"a", "b", "c"},
	"3": {"d", "e", "f"},
	"4": {"g", "h", "i"},
	"5": {"j", "k", "l"},
	"6": {"m", "n", "o"},
	"7": {"p", "q", "r", "s"},
	"8": {"t", "u", "v"},
	"9": {"w", "x", "y", "z"},
}

func letterCombinations(digits string) []string {
	if digits == "" {
		return make([]string, 0)
	}
	var result = table[string(digits[0])]
	for i := 1; i < len(digits); i++ {
		t := table[string(digits[i])]
		nr := make([]string, len(result)*len(t))
		for j := 0; j < len(result); j++ {
			for k := 0; k < len(t); k++ {
				nr[len(t)*j+k] = result[j] + t[k]
			}
		}
		result = nr
	}
	return result
}