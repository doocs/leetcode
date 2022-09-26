class Solution {
public:
    string reformatNumber(string number) {
        string s;
        for (char c : number) {
            if (c != ' ' && c != '-') {
                s.push_back(c);
            }
        }
        int n = s.size();
        vector<string> res;
        for (int i = 0; i < n / 3; ++i) {
            res.push_back(s.substr(i * 3, 3));
        }
        if (n % 3 == 1) {
            res.back() = res.back().substr(0, 2);
            res.push_back(s.substr(n - 2));
        } else if (n % 3 == 2) {
            res.push_back(s.substr(n - 2));
        }
        string ans;
        for (auto& v : res) {
            ans += v;
            ans += "-";
        }
        ans.pop_back();
        return ans;
    }
};