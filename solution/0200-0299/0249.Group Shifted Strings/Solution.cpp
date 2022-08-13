class Solution {
public:
    vector<vector<string>> groupStrings(vector<string>& strings) {
        unordered_map<string, vector<string>> mp;
        for (auto& s : strings) {
            int diff = s[0] - 'a';
            string t = s;
            for (int i = 0; i < t.size(); ++i) {
                char d = t[i] - diff;
                if (d < 'a') d += 26;
                t[i] = d;
            }
            cout << t << endl;
            mp[t].push_back(s);
        }
        vector<vector<string>> ans;
        for (auto& e : mp)
            ans.push_back(e.second);
        return ans;
    }
};