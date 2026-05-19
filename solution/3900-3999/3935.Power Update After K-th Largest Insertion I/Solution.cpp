class Solution {
public:
    using ll = long long;

    void add(map<int, int>& mp, int x) {
        ++mp[x];
    }

    void remove(map<int, int>& mp, int x) {
        if (--mp[x] == 0) {
            mp.erase(x);
        }
    }

    ll qpow(ll a, int b, int mod) {
        ll ans = 1;

        while (b) {
            if (b & 1) {
                ans = ans * a % mod;
            }

            a = a * a % mod;
            b >>= 1;
        }

        return ans;
    }

    vector<int> powerUpdate(vector<int>& nums, int p, vector<vector<int>>& queries) {
        map<int, int> l, r;

        int sz1 = 0, sz2 = nums.size();

        for (int x : nums) {
            add(r, x);
        }

        const int mod = 1e9 + 7;

        vector<int> ans;

        for (auto& q : queries) {
            int val = q[0];
            int k = q[1];

            add(r, val);
            ++sz2;

            int v = r.begin()->first;

            remove(r, v);
            --sz2;

            add(l, v);
            ++sz1;

            while (sz2 < k) {
                v = l.rbegin()->first;

                remove(l, v);
                --sz1;

                add(r, v);
                ++sz2;
            }

            while (sz2 > k) {
                v = r.begin()->first;

                remove(r, v);
                --sz2;

                add(l, v);
                ++sz1;
            }

            int x = r.begin()->first;

            p = qpow(p, x, mod);

            ans.push_back(p);
        }

        return ans;
    }
};