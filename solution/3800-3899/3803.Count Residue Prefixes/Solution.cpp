class Solution {
public:
    int residuePrefixes(string s) {
        unordered_set<char> st;
        int ans = 0;
        for (int i = 1; i <= s.size(); i++) {
            char c = s[i - 1];
            st.insert(c);
            if (st.size() == i % 3) {
                ans++;
            }
        }
        return ans;
    }
};
