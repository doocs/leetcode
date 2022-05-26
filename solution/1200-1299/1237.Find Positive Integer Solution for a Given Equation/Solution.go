/**
 * This is the declaration of customFunction API.
 * @param  x    int
 * @param  x    int
 * @return 	    Returns f(x, y) for any given positive integers x and y.
 *			    Note that f(x, y) is increasing with respect to both x and y.
 *              i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
 */

func findSolution(customFunction func(int, int) int, z int) [][]int {
	res := [][]int{}
	for i := 1; i <= 1000; i++ {
		left, right := 1, 1000
		for left < right {
			mid := (left + right) >> 1
			if customFunction(i, mid) >= z {
				right = mid
			} else {
				left = mid + 1
			}
		}
		if customFunction(i, left) == z {
			res = append(res, []int{i, left})
		}
	}
	return res
}