class Solution {
public:
    int minSwaps(string s) {
        int ans = 0;
        for (char& c : s) {
            if (c == '[')
                ++ans;
            else if (ans)
                --ans;
        }
        return (ans + 1) >> 1;
    }
};