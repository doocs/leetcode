class Solution {
public:
    int compress(vector<char>& chars) {
        int k = 0, n = chars.size();
        for (int i = 0, j = i + 1; i < n;) {
            while (j < n && chars[j] == chars[i])
                ++j;
            chars[k++] = chars[i];
            if (j - i > 1) {
                for (char c : to_string(j - i)) {
                    chars[k++] = c;
                }
            }
            i = j;
        }
        return k;
    }
};