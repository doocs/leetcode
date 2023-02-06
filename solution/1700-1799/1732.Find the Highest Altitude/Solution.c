#define max(a, b) (((a) > (b)) ? (a) : (b))

int largestAltitude(int* gain, int gainSize) {
    int ans = 0;
    int h = 0;
    for (int i = 0; i < gainSize; i++) {
        h += gain[i];
        ans = max(ans, h);
    }
    return ans;
}
