class Solution {
public:
    bool reorderedPowerOf2(int n) {
        vector<int> s = convert(n);
        for (int i = 1; i <= pow(10, 9); i <<= 1)
            if (s == convert(i))
                return true;
        return false;
    }

    vector<int> convert(int n) {
        vector<int> cnt(10);
        for (; n; n /= 10) ++cnt[n % 10];
        return cnt;
    }
};