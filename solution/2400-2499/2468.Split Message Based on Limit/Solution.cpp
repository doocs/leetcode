class Solution {
public:
    vector<string> splitMessage(string message, int limit) {
        int n = message.size();
        int sa = 0;
        vector<string> ans;
        for (int k = 1; k <= n; ++k) {
            int lk = to_string(k).size();
            sa += lk;
            int sb = lk * k;
            int sc = 3 * k;
            if (k * limit - (sa + sb + sc) >= n) {
                int i = 0;
                for (int j = 1; j <= k; ++j) {
                    string tail = "<" + to_string(j) + "/" + to_string(k) + ">";
                    string t = message.substr(i, limit - tail.size()) + tail;
                    ans.emplace_back(t);
                    i += limit - tail.size();
                }
                break;
            }
        }
        return ans;
    }
};