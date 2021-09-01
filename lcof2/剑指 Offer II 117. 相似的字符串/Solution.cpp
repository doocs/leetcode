class Solution {
public:
    vector<int> p;
    int numSimilarGroups(vector<string>& strs) {
        int n = strs.size();
        for (int i = 0; i < n; ++i) p.push_back(i);
        for (int i = 0; i < n; ++i)
        {
            for (int j = i + 1; j < n; ++j)
            {
                if (check(strs[i], strs[j]))
                    p[find(i)] = find(j);
            }
        }
        int res = 0;
        for (int i = 0; i < n; ++i)
        {
            if (i == find(i)) ++res;
        }
        return res;
    }

    bool check(string a, string b) {
        int cnt = 0;
        int n = a.size();
        for (int i = 0; i < n; ++i)
            if (a[i] != b[i]) ++cnt;
        return cnt <= 2;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};