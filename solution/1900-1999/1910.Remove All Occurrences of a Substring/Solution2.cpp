class Solution {
public:
    string removeOccurrences(string s, string part) {
        int m = part.size();
        string st;
        for (char c : s) {
            st.push_back(c);
            if ((int) st.size() >= m && st.compare(st.size() - m, m, part) == 0) {
                st.erase(st.size() - m);
            }
        }
        return st;
    }
};
