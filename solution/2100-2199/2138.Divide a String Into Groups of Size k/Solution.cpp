class Solution {
public:
    vector<string> divideString(string s, int k, char fill) {
        int n = s.size();
        if (n % k) {
            s += string(k - n % k, fill);
        }
        vector<string> ans;
        for (int i = 0; i < s.size() / k; ++i) {
            ans.push_back(s.substr(i * k, k));
        }
        return ans;
    }
};