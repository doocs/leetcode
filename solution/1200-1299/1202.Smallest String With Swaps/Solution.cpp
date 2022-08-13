class Solution {
public:
    vector<int> p;

    string smallestStringWithSwaps(string s, vector<vector<int>>& pairs) {
        int n = s.length();
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        for (auto& pair : pairs) p[find(pair[0])] = find(pair[1]);
        unordered_map<int, vector<char>> mp;
        for (int i = 0; i < n; ++i) mp[find(i)].push_back(s[i]);
        for (auto& [k, v] : mp) sort(v.rbegin(), v.rend());
        string ans;
        for (int i = 0; i < n; ++i) {
            ans.push_back(mp[find(i)].back());
            mp[find(i)].pop_back();
        }
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};