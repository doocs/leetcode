func smallestNumber(num string, t int64) string {
	primeCount, isDivisible := getPrimeCount(t)
	if !isDivisible {
		return "-1"
	}

	factorCount := getFactorCount(primeCount)
	if sumValues(factorCount) > len(num) {
		return construct(factorCount)
	}

	primeCountPrefix := getPrimeCountFromString(num)
	firstZeroIndex := strings.Index(num, "0")
	if firstZeroIndex == -1 {
		firstZeroIndex = len(num)
		if isSubset(primeCount, primeCountPrefix) {
			return num
		}
	}

	for i := len(num) - 1; i >= 0; i-- {
		d := int(num[i] - '0')
		primeCountPrefix = subtract(primeCountPrefix, kFactorCounts[d])
		spaceAfterThisDigit := len(num) - 1 - i
		if i > firstZeroIndex {
			continue
		}
		for biggerDigit := d + 1; biggerDigit < 10; biggerDigit++ {
			factorsAfterReplacement := getFactorCount(
				subtract(subtract(primeCount, primeCountPrefix), kFactorCounts[biggerDigit]),
			)
			if sumValues(factorsAfterReplacement) <= spaceAfterThisDigit {
				fillOnes := spaceAfterThisDigit - sumValues(factorsAfterReplacement)
				return num[:i] + strconv.Itoa(biggerDigit) + strings.Repeat("1", fillOnes) + construct(factorsAfterReplacement)
			}
		}
	}

	factorsAfterExtension := getFactorCount(primeCount)
	return strings.Repeat("1", len(num)+1-sumValues(factorsAfterExtension)) + construct(factorsAfterExtension)
}

var kFactorCounts = map[int]map[int]int{
	0: {}, 1: {}, 2: {2: 1}, 3: {3: 1}, 4: {2: 2},
	5: {5: 1}, 6: {2: 1, 3: 1}, 7: {7: 1}, 8: {2: 3}, 9: {3: 2},
}

func getPrimeCount(t int64) (map[int]int, bool) {
	count := map[int]int{2: 0, 3: 0, 5: 0, 7: 0}
	for _, prime := range []int{2, 3, 5, 7} {
		for t%int64(prime) == 0 {
			t /= int64(prime)
			count[prime]++
		}
	}
	return count, t == 1
}

func getPrimeCountFromString(num string) map[int]int {
	count := map[int]int{2: 0, 3: 0, 5: 0, 7: 0}
	for _, d := range num {
		for prime, freq := range kFactorCounts[int(d-'0')] {
			count[prime] += freq
		}
	}
	return count
}

func getFactorCount(count map[int]int) map[int]int {
	res := map[int]int{}
	count8 := count[2] / 3
	remaining2 := count[2] % 3
	count9 := count[3] / 2
	count3 := count[3] % 2
	count4 := remaining2 / 2
	count2 := remaining2 % 2
	count6 := 0
	if count2 == 1 && count3 == 1 {
		count2, count3 = 0, 0
		count6 = 1
	}
	if count3 == 1 && count4 == 1 {
		count2 = 1
		count6 = 1
		count3, count4 = 0, 0
	}
	res[2] = count2
	res[3] = count3
	res[4] = count4
	res[5] = count[5]
	res[6] = count6
	res[7] = count[7]
	res[8] = count8
	res[9] = count9
	return res
}

func construct(factors map[int]int) string {
	var res strings.Builder
	for digit := 2; digit < 10; digit++ {
		res.WriteString(strings.Repeat(strconv.Itoa(digit), factors[digit]))
	}
	return res.String()
}

func isSubset(a, b map[int]int) bool {
	for key, value := range a {
		if b[key] < value {
			return false
		}
	}
	return true
}

func subtract(a, b map[int]int) map[int]int {
	res := make(map[int]int, len(a))
	for k, v := range a {
		res[k] = v
	}
	for k, v := range b {
		res[k] = max(0, res[k]-v)
	}
	return res
}

func sumValues(count map[int]int) int {
	sum := 0
	for _, v := range count {
		sum += v
	}
	return sum
}
