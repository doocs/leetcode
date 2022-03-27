class Solution {
public:
    bool hasGroupsSizeX(vector<int>& deck) {
        vector<int> counter(10000);
        for (int& d : deck) ++counter[d];
        int g = -1;
        for (int& v : counter)
            if (v > 0)
                g = g == -1 ? v : gcd(g, v);
        return g >= 2;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
};