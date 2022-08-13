class Solution {
public:
    const int MAX_WIDTH = 100;

    vector<int> numberOfLines(vector<int>& widths, string s) {
        int last = 0, row = 1;
        for (char c : s) {
            int w = widths[c - 'a'];
            if (last + w <= MAX_WIDTH)
                last += w;
            else {
                ++row;
                last = w;
            }
        }
        return {row, last};
    }
};