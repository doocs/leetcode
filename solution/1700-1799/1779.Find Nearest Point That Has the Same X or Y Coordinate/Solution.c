int nearestValidPoint(int x, int y, int** points, int pointsSize, int* pointsColSize) {
    int ans = -1;
    int min = INT_MAX;
    for (int i = 0; i < pointsSize; i++) {
        int* point = points[i];
        if (point[0] != x && point[1] != y) {
            continue;
        }
        int d = abs(x - point[0]) + abs(y - point[1]);
        if (d < min) {
            min = d;
            ans = i;
        }
    }
    return ans;
}
