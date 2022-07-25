class Solution {
public:
    vector<int> numMovesStones(int a, int b, int c) {
        int x = min(min(a, b), c);
        int z = max(max(a, b), c);
        int y = a + b + c - x - z;
        if (z - x == 2) return {0, 0};
        int mx = z - x - 2;
        int mi = y - x < 3 || z - y < 3 ? 1 : 2;
        return {mi, mx};
    }
};