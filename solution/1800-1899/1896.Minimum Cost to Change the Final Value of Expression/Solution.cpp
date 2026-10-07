class Solution {
public:
    int minOperationsToFlip(string expression) {
        vector<pair<int, int>> nums;
        vector<char> ops;
        auto merge = [&](char op) {
            auto b = nums.back();
            nums.pop_back();
            auto a = nums.back();
            nums.pop_back();
            int v1 = a.first, c1 = a.second, v2 = b.first, c2 = b.second;
            int val, cost;
            if (op == '&') {
                val = v1 & v2;
                if (v1 == 1 && v2 == 1) {
                    cost = min(c1, c2);
                } else if (v1 == 0 && v2 == 0) {
                    cost = min(c1 + c2, 1 + min(c1, c2));
                } else {
                    cost = min(v1 == 0 ? c1 : c2, 1);
                }
            } else {
                val = v1 | v2;
                if (v1 == 0 && v2 == 0) {
                    cost = min(c1, c2);
                } else if (v1 == 1 && v2 == 1) {
                    cost = min(c1 + c2, 1 + min(c1, c2));
                } else {
                    cost = min(v1 == 1 ? c1 : c2, 1);
                }
            }
            nums.emplace_back(val, cost);
        };
        for (char c : expression) {
            if (c == '(') {
                ops.push_back(c);
            } else if (c == '0' || c == '1') {
                nums.emplace_back(c - '0', 1);
            } else if (c == '&' || c == '|') {
                while (!ops.empty() && (ops.back() == '&' || ops.back() == '|')) {
                    merge(ops.back());
                    ops.pop_back();
                }
                ops.push_back(c);
            } else {
                while (ops.back() != '(') {
                    merge(ops.back());
                    ops.pop_back();
                }
                ops.pop_back();
            }
        }
        while (!ops.empty()) {
            merge(ops.back());
            ops.pop_back();
        }
        return nums[0].second;
    }
};
