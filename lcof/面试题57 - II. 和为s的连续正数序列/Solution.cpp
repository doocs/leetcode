class Solution {
public:
    vector<int> build(int small, int big) {
        vector<int> ret;
        for (int i = small; i <= big; i++) {
            ret.push_back(i);
        }

        return ret;
    }

    vector<vector<int>> findContinuousSequence(int target) {
        vector<vector<int>> ret;
        int small = 1;
        int big = 2;
        int mid = (target + 1) / 2;
        int curSum = small + big;

        if (target < 3) {
            ret;
        }

        while (small < mid) {
            if (curSum == target) {
                ret.push_back(build(small, big));
            }

            while (curSum > target && small < mid) {
                // 一直减去，减去到比target小停止
                curSum -= small;
                small++;

                if (curSum == target && small < mid) {
                    ret.push_back(build(small, big));
                }
            }

            big++;
            curSum += big;
        }

        return ret;
    }
};