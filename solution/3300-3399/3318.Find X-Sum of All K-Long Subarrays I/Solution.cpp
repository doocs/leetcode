class Solution {
public:
    vector<int> findXSum(vector<int>& nums, int k, int x) {
        using pii = pair<int, int>;
        set<pii> l, r;
        int s = 0;
        unordered_map<int, int> cnt;
        auto add = [&](int v) {
            if (cnt[v] == 0) {
                return;
            }
            pii p = {cnt[v], v};
            if (!l.empty() && p > *l.begin()) {
                s += p.first * p.second;
                l.insert(p);
            } else {
                r.insert(p);
            }
        };
        auto remove = [&](int v) {
            if (cnt[v] == 0) {
                return;
            }
            pii p = {cnt[v], v};
            auto it = l.find(p);
            if (it != l.end()) {
                s -= p.first * p.second;
                l.erase(it);
            } else {
                r.erase(p);
            }
        };
        vector<int> ans;
        for (int i = 0; i < nums.size(); ++i) {
            remove(nums[i]);
            ++cnt[nums[i]];
            add(nums[i]);

            int j = i - k + 1;
            if (j < 0) {
                continue;
            }

            while (!r.empty() && l.size() < x) {
                pii p = *r.rbegin();
                s += p.first * p.second;
                r.erase(p);
                l.insert(p);
            }
            while (l.size() > x) {
                pii p = *l.begin();
                s -= p.first * p.second;
                l.erase(p);
                r.insert(p);
            }
            ans.push_back(s);

            remove(nums[j]);
            --cnt[nums[j]];
            add(nums[j]);
        }
        return ans;
    }
};
