class Solution {
public:
    vector<int> fullBloomFlowers(vector<vector<int>>& flowers, vector<int>& people) {
        map<int, int> d;
        for (auto& f : flowers) {
            d[f[0]]++;
            d[f[1] + 1]--;
        }
        int m = people.size();
        vector<int> idx(m);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) {
            return people[i] < people[j];
        });
        vector<int> ans(m);
        int s = 0;
        for (int i : idx) {
            int t = people[i];
            while (!d.empty() && d.begin()->first <= t) {
                s += d.begin()->second;
                d.erase(d.begin());
            }
            ans[i] = s;
        }
        return ans;
    }
};