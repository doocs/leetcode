class Solution {
public:
    string compressString(string S) {
        int n = S.size();
        string t;
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && S[j] == S[i]) {
                ++j;
            }
            t += S[i];
            t += to_string(j - i);
            i = j;
        }
        return t.size() < n ? t : S;
    }
};