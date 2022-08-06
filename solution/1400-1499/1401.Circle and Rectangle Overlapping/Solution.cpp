class Solution {
public:
    bool checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int dx = x1 > xCenter ? x1 - xCenter : (x2 < xCenter ? xCenter - x2 : 0);
        int dy = y1 > yCenter ? y1 - yCenter : (y2 < yCenter ? yCenter - y2 : 0);
        return dx * dx + dy * dy <= radius * radius;
    }
};