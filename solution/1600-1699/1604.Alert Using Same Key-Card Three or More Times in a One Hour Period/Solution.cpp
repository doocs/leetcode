class Solution {
public:
    vector<string> alertNames(vector<string>& keyName, vector<string>& keyTime) {
        unordered_map<string, vector<int>> d;
        for (int i = 0; i < keyName.size(); ++i) {
            auto name = keyName[i];
            auto time = keyTime[i];
            int a, b;
            sscanf(time.c_str(), "%d:%d", &a, &b);
            int t = a * 60 + b;
            d[name].emplace_back(t);
        }
        vector<string> ans;
        for (auto& [name, ts] : d) {
            int n = ts.size();
            if (n > 2) {
                sort(ts.begin(), ts.end());
                for (int i = 0; i < n - 2; ++i) {
                    if (ts[i + 2] - ts[i] <= 60) {
                        ans.emplace_back(name);
                        break;
                    }
                }
            }
        }
        sort(ans.begin(), ans.end());
        return ans;
    }
};