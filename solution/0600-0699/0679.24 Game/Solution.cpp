class Solution {
public:
    bool judgePoint24(vector<int>& cards) {
        vector<double> nums;
        for (int num : cards) {
            nums.push_back(static_cast<double>(num));
        }
        return dfs(nums);
    }

private:
    const char ops[4] = {'+', '-', '*', '/'};

    bool dfs(vector<double>& nums) {
        int n = nums.size();
        if (n == 1) {
            return abs(nums[0] - 24) < 1e-6;
        }
        bool ok = false;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != j) {
                    vector<double> nxt;
                    for (int k = 0; k < n; ++k) {
                        if (k != i && k != j) {
                            nxt.push_back(nums[k]);
                        }
                    }
                    for (char op : ops) {
                        switch (op) {
                        case '/':
                            if (nums[j] == 0) {
                                continue;
                            }
                            nxt.push_back(nums[i] / nums[j]);
                            break;
                        case '*':
                            nxt.push_back(nums[i] * nums[j]);
                            break;
                        case '+':
                            nxt.push_back(nums[i] + nums[j]);
                            break;
                        case '-':
                            nxt.push_back(nums[i] - nums[j]);
                            break;
                        }
                        ok |= dfs(nxt);
                        if (ok) {
                            return true;
                        }
                        nxt.pop_back();
                    }
                }
            }
        }
        return ok;
    }
};