class MedianCost {
    multiset<int> l, r;
    long long s1 = 0, s2 = 0;

public:
    void adjust() {
        while ((long long) l.size() - (long long) r.size() > 1) {
            int v = *l.rbegin();
            l.erase(prev(l.end()));
            s1 -= v;
            r.insert(v);
            s2 += v;
        }
        while (r.size() > l.size()) {
            int v = *r.begin();
            r.erase(r.begin());
            s2 -= v;
            l.insert(v);
            s1 += v;
        }
    }

    void add(int x) {
        r.insert(x);
        s2 += x;
        int y = *r.begin();
        r.erase(r.begin());
        s2 -= y;
        l.insert(y);
        s1 += y;
        adjust();
    }

    void remove(int x) {
        auto it = l.find(x);
        if (it != l.end()) {
            l.erase(it);
            s1 -= x;
        } else {
            r.erase(r.find(x));
            s2 -= x;
        }
        adjust();
    }

    long long query() {
        long long mid = *l.rbegin();
        return (long long) l.size() * mid - s1 + s2 - (long long) r.size() * mid;
    }
};

class Solution {
public:
    long long minOperations(vector<int>& nums, int x, int k) {
        int n = nums.size();
        const long long INF = LLONG_MAX / 4;
        vector<vector<long long>> f(n + 1, vector<long long>(k + 1, INF));
        f[0][0] = 0;

        MedianCost mc;
        for (int i = 1; i <= n; ++i) {
            mc.add(nums[i - 1]);
            long long w = mc.query();

            for (int j = 0; j <= k; ++j) {
                f[i][j] = f[i - 1][j];
                if (j > 0 && i >= x) {
                    f[i][j] = min(f[i][j], f[i - x][j - 1] + w);
                }
            }

            if (i >= x) {
                mc.remove(nums[i - x]);
            }
        }
        return f[n][k];
    }
};