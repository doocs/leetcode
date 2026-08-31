class Solution {
public:
    int maxValidSplits(vector<int>& nums) {
        int n = nums.size();

        vector<bool> pos1 = mark(nums);

        vector<int> rev = nums;
        reverse(rev.begin(), rev.end());
        vector<bool> pos2 = mark(rev);

        int ans = calc(nums);

        for (int i = 0; i < n; ++i) {
            if (pos1[i] || pos2[n - 1 - i]) {
                vector<int> arr;
                arr.reserve(n - 1);

                for (int j = 0; j < n; ++j) {
                    if (i != j) {
                        arr.push_back(nums[j]);
                    }
                }

                ans = max(ans, calc(arr));
            }
        }

        return ans;
    }

private:
    vector<bool> mark(const vector<int>& nums) {
        int n = nums.size();
        vector<bool> pos(n);

        pos[0] = true;
        int g = nums[0];

        for (int i = 1; i < n; ++i) {
            int ng = gcd(g, nums[i]);
            pos[i] = ng != g;
            g = ng;
        }

        return pos;
    }

    int calc(const vector<int>& arr) {
        int n = arr.size();
        vector<int> pre(n), suf(n);

        pre[0] = arr[0];
        for (int i = 1; i < n; ++i) {
            pre[i] = gcd(pre[i - 1], arr[i]);
        }

        suf[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            suf[i] = gcd(suf[i + 1], arr[i]);
        }

        int ans = 0;
        for (int i = 0; i + 1 < n; ++i) {
            if (pre[i] == suf[i + 1]) {
                ++ans;
            }
        }

        return ans;
    }
};
