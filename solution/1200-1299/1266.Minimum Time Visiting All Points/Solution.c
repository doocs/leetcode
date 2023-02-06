#define max(a, b) (((a) > (b)) ? (a) : (b))

int minTimeToVisitAllPoints(int** points, int pointsSize, int* pointsColSize) {
    int ans = 0;
    for (int i = 1; i < pointsSize; i++) {
        int x = abs(points[i - 1][0] - points[i][0]);
        int y = abs(points[i - 1][1] - points[i][1]);
        ans += max(x, y);
    }
    return ans;
}
