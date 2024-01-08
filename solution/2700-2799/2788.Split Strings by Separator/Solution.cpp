class Solution {
public:
    vector<string> splitWordsBySeparator(vector<string>& words, char separator) {
        vector<string> ans;
        for (const auto& w : words) {
            istringstream ss(w);
            string s;
            while (getline(ss, s, separator)) {
                if (!s.empty()) {
                    ans.push_back(s);
                }
            }
        }
        return ans;
    }
};