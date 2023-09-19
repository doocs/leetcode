class Solution {
public:
    vector<int> closestRoom(vector<vector<int>>& rooms, vector<vector<int>>& queries) {
        int n = rooms.size();
        int k = queries.size();
        sort(rooms.begin(), rooms.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[1] < b[1];
        });
        vector<int> idx(k);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) {
            return queries[i][1] < queries[j][1];
        });
        vector<int> ans(k, -1);
        int i = 0;
        multiset<int> s;
        for (auto& room : rooms) {
            s.insert(room[0]);
        }
        for (int j : idx) {
            int prefer = queries[j][0], minSize = queries[j][1];
            while (i < n && rooms[i][1] < minSize) {
                s.erase(s.find(rooms[i][0]));
                ++i;
            }
            if (i == n) {
                break;
            }
            auto it = s.lower_bound(prefer);
            if (it != s.end()) {
                ans[j] = *it;
            }
            if (it != s.begin()) {
                --it;
                if (ans[j] == -1 || abs(*it - prefer) <= abs(ans[j] - prefer)) {
                    ans[j] = *it;
                }
            }
        }
        return ans;
    }
};