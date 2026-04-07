var GOOD []int

func init() {
	const LIMIT = 1e9

	cnt := make(map[int]int)
	cubes := make([]int, 1001)

	for i := 0; i <= 1000; i++ {
		cubes[i] = i * i * i
	}

	for a := 1; a <= 1000; a++ {
		for b := a; b <= 1000; b++ {
			x := cubes[a] + cubes[b]
			if x > LIMIT {
				break
			}
			cnt[x]++
		}
	}

	for x, v := range cnt {
		if v > 1 {
			GOOD = append(GOOD, x)
		}
	}

	sort.Ints(GOOD)
}

func findGoodIntegers(n int) []int {
	idx := sort.Search(len(GOOD), func(i int) bool {
		return GOOD[i] > n
	})
	return GOOD[:idx]
}
