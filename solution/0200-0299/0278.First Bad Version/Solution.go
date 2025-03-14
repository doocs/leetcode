/**
 * Forward declaration of isBadVersion API.
 * @param   version   your guess about first bad version
 * @return 	 	      true if current version is bad
 *			          false if current version is good
 * func isBadVersion(version int) bool;
 */

func firstBadVersion(n int) int {
	l, r := 1, n
	for l < r {
		mid := (l + r) >> 1
		if isBadVersion(mid) {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}
