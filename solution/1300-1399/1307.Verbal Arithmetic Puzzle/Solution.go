func isSolvable(words []string, result string) bool {
	words = append(words, result)
	totalRows := len(words)
	totalCols := 0
	for _, w := range words {
		if len(w) > totalCols {
			totalCols = len(w)
		}
	}
	letToDig := map[byte]int{}
	digToLet := [10]byte{}
	for i := range digToLet {
		digToLet[i] = '-'
	}
	var isAnyMapping func(row, col, bal int) bool
	isAnyMapping = func(row, col, bal int) bool {
		if col == totalCols {
			return bal == 0
		}
		if row == totalRows {
			return bal%10 == 0 && isAnyMapping(0, col+1, bal/10)
		}
		w := words[row]
		if col >= len(w) {
			return isAnyMapping(row+1, col, bal)
		}
		letter := w[len(w)-1-col]
		sign := 1
		if row == totalRows-1 {
			sign = -1
		}
		if d, ok := letToDig[letter]; ok && (d != 0 || (d == 0 && len(w) == 1) || col != len(w)-1) {
			return isAnyMapping(row+1, col, bal+sign*d)
		}
		for i := 0; i < 10; i++ {
			if digToLet[i] == '-' && (i != 0 || (i == 0 && len(w) == 1) || col != len(w)-1) {
				digToLet[i] = letter
				letToDig[letter] = i
				if isAnyMapping(row+1, col, bal+sign*letToDig[letter]) {
					return true
				}
				digToLet[i] = '-'
				delete(letToDig, letter)
			}
		}
		return false
	}
	return isAnyMapping(0, 0, 0)
}
