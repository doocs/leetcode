class Solution {
public:
    int n;

    int findKthNumber(int n, int k) {
        this->n = n;
        --k;
        long long curr = 1;
        while (k) {
            int cnt = count(curr);
            if (k >= cnt) {
                k -= cnt;
                ++curr;
            } else {
                --k;
                curr *= 10;
            }
        }
        return (int)curr;
    }

    int count(long long curr) {
        long long next = curr + 1;
        int cnt = 0;
        while (curr <= n) {
            cnt += min(n - curr + 1, next - curr);
            next *= 10;
            curr *= 10;
        }
        return cnt;
    }
};