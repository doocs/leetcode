class SparseTableRMQ {
public:
    int n;
    int maxLog;
    vector<vector<int>> fMax;
    vector<vector<int>> fMin;
    vector<int> lg;

    SparseTableRMQ(const vector<int>& data) {
        n = data.size();
        maxLog = 32 - __builtin_clz(n) + 1;
        fMax.assign(n, vector<int>(maxLog, 0));
        fMin.assign(n, vector<int>(maxLog, 0));
        lg.assign(n + 1, 0);

        for (int i = 2; i <= n; i++) {
            lg[i] = lg[i >> 1] + 1;
        }

        for (int i = 0; i < n; i++) {
            fMax[i][0] = data[i];
            fMin[i][0] = data[i];
        }

        for (int j = 1; j < maxLog; j++) {
            for (int i = 0; i <= n - (1 << j); i++) {
                fMax[i][j] = max(fMax[i][j - 1], fMax[i + (1 << (j - 1))][j - 1]);
                fMin[i][j] = min(fMin[i][j - 1], fMin[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    int queryMax(int l, int r) {
        int k = lg[r - l + 1];
        return max(fMax[l][k], fMax[r - (1 << k) + 1][k]);
    }

    int queryMin(int l, int r) {
        int k = lg[r - l + 1];
        return min(fMin[l][k], fMin[r - (1 << k) + 1][k]);
    }
};

class Solution {
public:
    long long maxTotalValue(vector<int>& nums, int k) {
        int n = nums.size();
        SparseTableRMQ st(nums);
        auto cmp = [](const tuple<long long, int, int>& a, const tuple<long long, int, int>& b) {
            return get<0>(a) < get<0>(b);
        };
        priority_queue<tuple<long long, int, int>, vector<tuple<long long, int, int>>, decltype(cmp)> pq(cmp);

        for (int l = 0; l < n; l++) {
            long long val = st.queryMax(l, n - 1) - st.queryMin(l, n - 1);
            pq.push({val, l, n - 1});
        }

        long long ans = 0;
        for (int i = 0; i < k; i++) {
            auto curr = pq.top();
            pq.pop();
            long long val = get<0>(curr);
            int l = get<1>(curr);
            int r = get<2>(curr);
            ans += val;
            if (r > l) {
                long long nextVal = st.queryMax(l, r - 1) - st.queryMin(l, r - 1);
                pq.push({nextVal, l, r - 1});
            }
        }
        return ans;
    }
};