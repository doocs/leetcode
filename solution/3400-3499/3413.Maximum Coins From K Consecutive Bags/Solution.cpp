class Solution {
private:
    struct interval {
        int start = -1, end = -1, value = 0;

        interval() = default;
        interval(int _start, int _end, int _value)
            : start(_start)
            , end(_end)
            , value(_value) {}

        bool operator<(const interval& other) const {
            return start < other.start || (start == other.start && end < other.end);
        }
        int64_t cost() const { return int64_t(value) * (end - start + 1); }
        int64_t cost(int len) const { return int64_t(value) * len; }
    };
public:
    long long maximumCoins(vector<vector<int>>& coins, int k) {
        int N = int(coins.size());
        vector<interval> A(N);

        for (int i = 0; i < N; i++) A[i] = {coins[i][0], coins[i][1], coins[i][2]};
        sort(A.begin(), A.end());

        int64_t ans = 0LL;
        {
            int64_t value = 0LL;
            int right = 0;
            for (int left = 0; left < N; left++) {
                while (right < N && A[right].end - A[left].start < k) {
                    value += A[right].cost();
                    ++right;
                }

                if (right < N) {
                    int64_t extra = (A[right].start - A[left].start <= k) ? A[right].cost(k - A[right].start + A[left].start) : 0LL;
                    ans = max(ans, value + extra);
                }

                value -= A[left].cost();
            }
        }

        {
            int64_t value = 0LL;
            int left = 0;
            for (int right = 0; right < N; right++) {
                value += A[right].cost();
                while (A[right].end - A[left].end >= k) {
                    value -= A[left].cost();
                    left++;
                }

                int64_t extra = (A[right].end - A[left].start >= k) ? A[left].cost(A[right].end - A[left].start - k + 1) : 0LL;
                ans = max(ans, value - extra);
            }
        }

        return ans;
    }
};
