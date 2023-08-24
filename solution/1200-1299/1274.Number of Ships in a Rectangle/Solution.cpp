/**
 * // This is Sea's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Sea {
 *   public:
 *     bool hasShips(vector<int> topRight, vector<int> bottomLeft);
 * };
 */

class Solution {
public:
    int countShips(Sea sea, vector<int> topRight, vector<int> bottomLeft) {
        int x1 = bottomLeft[0], y1 = bottomLeft[1];
        int x2 = topRight[0], y2 = topRight[1];
        if (x1 > x2 || y1 > y2) {
            return 0;
        }
        if (!sea.hasShips(topRight, bottomLeft)) {
            return 0;
        }
        if (x1 == x2 && y1 == y2) {
            return 1;
        }
        int midx = (x1 + x2) >> 1;
        int midy = (y1 + y2) >> 1;
        int a = countShips(sea, topRight, {midx + 1, midy + 1});
        int b = countShips(sea, {midx, y2}, {x1, midy + 1});
        int c = countShips(sea, {midx, midy}, bottomLeft);
        int d = countShips(sea, {x2, midy}, {midx + 1, y1});
        return a + b + c + d;
    }
};