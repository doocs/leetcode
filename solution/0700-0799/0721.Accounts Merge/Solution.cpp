class Solution {
public:
    vector<int> p;

    vector<vector<string>> accountsMerge(vector<vector<string>>& accounts) {
        int n = accounts.size();
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        unordered_map<string, int> emailId;
        for (int i = 0; i < n; ++i) {
            auto account = accounts[i];
            auto name = account[0];
            for (int j = 1; j < account.size(); ++j) {
                string email = account[j];
                if (emailId.count(email))
                    p[find(i)] = find(emailId[email]);
                else
                    emailId[email] = i;
            }
        }
        unordered_map<int, unordered_set<string>> mp;
        for (int i = 0; i < n; ++i) {
            auto account = accounts[i];
            for (int j = 1; j < account.size(); ++j) {
                string email = account[j];
                mp[find(i)].insert(email);
            }
        }
        vector<vector<string>> ans;
        for (auto& [i, emails] : mp) {
            vector<string> t;
            t.push_back(accounts[i][0]);
            for (string email : emails) t.push_back(email);
            sort(t.begin() + 1, t.end());
            ans.push_back(t);
        }
        return ans;
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
};