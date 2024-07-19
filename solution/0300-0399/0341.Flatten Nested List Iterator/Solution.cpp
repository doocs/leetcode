/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * class NestedInteger {
 *   public:
 *     // Return true if this NestedInteger holds a single integer, rather than a nested list.
 *     bool isInteger() const;
 *
 *     // Return the single integer that this NestedInteger holds, if it holds a single integer
 *     // The result is undefined if this NestedInteger holds a nested list
 *     int getInteger() const;
 *
 *     // Return the nested list that this NestedInteger holds, if it holds a nested list
 *     // The result is undefined if this NestedInteger holds a single integer
 *     const vector<NestedInteger> &getList() const;
 * };
 */

class NestedIterator {
public:
    NestedIterator(vector<NestedInteger>& nestedList) {
        auto dfs = [&](auto&& dfs, vector<NestedInteger>& ls) -> void {
            for (auto& x : ls) {
                if (x.isInteger()) {
                    nums.push_back(x.getInteger());
                } else {
                    dfs(dfs, x.getList());
                }
            }
        };
        dfs(dfs, nestedList);
    }

    int next() {
        return nums[++i];
    }

    bool hasNext() {
        return i + 1 < nums.size();
    }

private:
    vector<int> nums;
    int i = -1;
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */