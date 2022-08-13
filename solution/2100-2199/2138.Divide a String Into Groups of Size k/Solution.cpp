class Solution {
public:
    vector<string> divideString(string s, int k, char fill) {
        int n = s.size();
        if (n % k)
            for (int i = 0; i < k - n % k; ++i) s.push_back(fill);
        vector<string> ans;
        for (int i = 0; i < s.size() / k; ++i) ans.push_back(s.substr(i * k, k));
        return ans;
    }
};