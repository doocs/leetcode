class Solution {
public:
    long long flowerGame(int n, int m) {
        long long count = (n + 1) / 2;
        long long tol = (m + 1) / 2;
        long long ecount = n / 2;
        long long etol = m / 2;
        return (count * etol + ecount * tol);
    }
};
