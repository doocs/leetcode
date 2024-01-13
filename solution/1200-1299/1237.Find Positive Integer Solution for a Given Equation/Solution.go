/**
 * This is the declaration of customFunction API.
 * @param  x    int
 * @param  x    int
 * @return 	    Returns f(x, y) for any given positive integers x and y.
 *			    Note that f(x, y) is increasing with respect to both x and y.
 *              i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
 */

func findSolution(customFunction func(int, int) int, z int) (ans [][]int) {
	for x := 1; x <= 1000; x++ {
		y := 1 + sort.Search(999, func(y int) bool { return customFunction(x, y+1) >= z })
		if customFunction(x, y) == z {
			ans = append(ans, []int{x, y})
		}
	}
	return
}