class Solution {
public:
    int countOddLetters(int n) {
        static const unordered_map<int, string> d = {
            {0, "zero"},
            {1, "one"},
            {2, "two"},
            {3, "three"},
            {4, "four"},
            {5, "five"},
            {6, "six"},
            {7, "seven"},
            {8, "eight"},
            {9, "nine"}};

        int mask = 0;
        while (n > 0) {
            int x = n % 10;
            n /= 10;
            for (char c : d.at(x)) {
                mask ^= 1 << (c - 'a');
            }
        }
        return __builtin_popcount(mask);
    }
};
