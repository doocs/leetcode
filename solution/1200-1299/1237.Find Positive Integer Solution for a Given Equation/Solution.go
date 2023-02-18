/**
 * This is the declaration of customFunction API.
 * @param  x    int
 * @param  x    int
 * @return 	    Returns f(x, y) for any given positive integers x and y.
 *			    Note that f(x, y) is increasing with respect to both x and y.
 *              i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
 */

func findSolution(customFunction func(int, int) int, z int) (ans [][]int) {
	x, y := 1, 1000
	for x <= 1000 && y > 0 {
		t := customFunction(x, y)
		if t < z {
			x++
		} else if t > z {
			y--
		} else {
			ans = append(ans, []int{x, y})
			x, y = x+1, y-1
		}
	}
	return
}