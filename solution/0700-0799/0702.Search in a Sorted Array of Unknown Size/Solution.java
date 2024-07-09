/**
 * // This is ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *     public int get(int index) {}
 * }
 */

class Solution {
    public int search(ArrayReader reader, int target) {
        int r = 1;
        while (reader.get(r) < target) {
            r <<= 1;
        }
        int l = r >> 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (reader.get(mid) >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return reader.get(l) == target ? l : -1;
    }
}