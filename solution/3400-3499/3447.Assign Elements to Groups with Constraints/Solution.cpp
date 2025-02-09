class Solution {
public:
    vector<int> assignElements(vector<int>& groups, vector<int>& elements) {
        int mx = ranges::max(groups);
        vector<int> d(mx + 1, -1);

        for (int j = 0; j < elements.size(); ++j) {
            int x = elements[j];
            if (x > mx || d[x] != -1) {
                continue;
            }
            for (int y = x; y <= mx; y += x) {
                if (d[y] == -1) {
                    d[y] = j;
                }
            }
        }

        vector<int> ans(groups.size());
        for (int i = 0; i < groups.size(); ++i) {
            ans[i] = d[groups[i]];
        }

        return ans;
    }
};
