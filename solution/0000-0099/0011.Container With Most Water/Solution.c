int maxArea(int* h, int n) {
    int l = 0, r = n - 1, max = 0;
    while (l < r) {
        int area = (r - l) * (h[l] < h[r] ? h[l++] : h[r--]);
        if (area > max)
            max = area;
    }
    return max;
}
