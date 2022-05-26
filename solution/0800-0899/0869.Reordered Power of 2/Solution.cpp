class Solution {
public:
    bool reorderedPowerOf2(int n) {
        vector<int> s = convert(n);
        for (int i = 1; i <= pow(10, 9); i <<= 1)
            if (s == convert(i)) return true;
        return false;
    }

    vector<int> convert(int n) {
        vector<int> counter(10);
        while (n) 
        {
            ++counter[n % 10];
            n /= 10;
        }
        return counter;
    }
};