struct T {
    long sum;
    long maxSubarraySumPrefix;
    long maxSubarraySumSuffix;
    long maxSubarraySum;
    T() = default;
    T(int num)
        : sum(num)
        , maxSubarraySumPrefix(num)
        , maxSubarraySumSuffix(num)
        , maxSubarraySum(num) {}
    T(long sum, long prefix, long suffix, long maxSum)
        : sum(sum)
        , maxSubarraySumPrefix(prefix)
        , maxSubarraySumSuffix(suffix)
        , maxSubarraySum(maxSum) {}
};

class SegmentTree {
public:
    SegmentTree(const vector<int>& nums)
        : n(nums.size())
        , tree(nums.size() * 4) {
        build(nums, 0, 0, n - 1);
    }

    // Updates nums[i] to val.
    void update(int i, int val) {
        update(0, 0, n - 1, i, val);
    }

    long getMaxSubarraySum() const {
        return tree[0].maxSubarraySum;
    }

private:
    const int n; // the size of the input array
    vector<T> tree; // the segment tree

    void build(const vector<int>& nums, int treeIndex, int lo, int hi) {
        if (lo == hi) {
            tree[treeIndex] = T(nums[lo]);
            return;
        }
        const int mid = (lo + hi) / 2;
        build(nums, 2 * treeIndex + 1, lo, mid);
        build(nums, 2 * treeIndex + 2, mid + 1, hi);
        tree[treeIndex] = merge(tree[2 * treeIndex + 1], tree[2 * treeIndex + 2]);
    }

    void update(int treeIndex, int lo, int hi, int i, int val) {
        if (lo == hi) {
            tree[treeIndex] = T(val);
            return;
        }
        const int mid = (lo + hi) / 2;
        if (i <= mid)
            update(2 * treeIndex + 1, lo, mid, i, val);
        else
            update(2 * treeIndex + 2, mid + 1, hi, i, val);
        tree[treeIndex] = merge(tree[2 * treeIndex + 1], tree[2 * treeIndex + 2]);
    }

    T merge(const T& left, const T& right) const {
        return T(
            left.sum + right.sum,
            max(left.maxSubarraySumPrefix, left.sum + right.maxSubarraySumPrefix),
            max(right.maxSubarraySumSuffix, right.sum + left.maxSubarraySumSuffix),
            max({left.maxSubarraySum, right.maxSubarraySum,
                left.maxSubarraySumSuffix + right.maxSubarraySumPrefix}));
    }
};

class Solution {
public:
    long long maxSubarraySum(vector<int>& nums) {
        const bool allPositives = ranges::all_of(nums, [](int num) { return num >= 0; });
        const long sum = accumulate(nums.begin(), nums.end(), 0L);
        if (allPositives)
            return sum;
        const int maxNum = ranges::max(nums);
        if (maxNum < 0)
            return maxNum;

        long ans = LONG_MIN;
        unordered_map<int, vector<int>> numToIndices;
        SegmentTree tree(nums);

        for (int i = 0; i < nums.size(); ++i)
            numToIndices[nums[i]].push_back(i);

        for (const auto& [num, indices] : numToIndices) {
            for (const int index : indices)
                tree.update(index, 0);
            ans = max(ans, tree.getMaxSubarraySum());
            for (const int index : indices)
                tree.update(index, num);
        }

        return ans;
    }
};
