class Solution {
public:
    vector<double> cutSquares(vector<int>& square1, vector<int>& square2) {
        double x1 = square1[0] + square1[2] / 2.0;
        double y1 = square1[1] + square1[2] / 2.0;
        double x2 = square2[0] + square2[2] / 2.0;
        double y2 = square2[1] + square2[2] / 2.0;
        if (x1 == x2) {
            double y3 = min(square1[1], square2[1]);
            double y4 = max(square1[1] + square1[2], square2[1] + square2[2]);
            return {x1, y3, x2, y4};
        }
        double k = (y2 - y1) / (x2 - x1);
        double b = y1 - k * x1;
        if (abs(k) > 1) {
            double y3 = min(square1[1], square2[1]);
            double x3 = (y3 - b) / k;
            double y4 = max(square1[1] + square1[2], square2[1] + square2[2]);
            double x4 = (y4 - b) / k;
            if (x3 > x4 || (x3 == x4 && y3 > y4)) {
                return {x4, y4, x3, y3};
            }
            return {x3, y3, x4, y4};
        } else {
            double x3 = min(square1[0], square2[0]);
            double y3 = k * x3 + b;
            double x4 = max(square1[0] + square1[2], square2[0] + square2[2]);
            double y4 = k * x4 + b;
            return {x3, y3, x4, y4};
        }
    }
};