class Solution {
public:
    vector<string> splitWordsBySeparator(vector<string>& words, char separator) {
        vector<string> ans;
        for (auto& w : words) {
            for (auto& s : split(w, separator)) {
                if (!s.empty()) {
                    ans.emplace_back(s);
                }
            }
        }
        return ans;
    }

    vector<string> split(string& s, char c) {
        vector<string> res;
        stringstream ss(s);
        string t;
        while (getline(ss, t, c)) {
            res.push_back(t);
        }
        return res;
    }
};