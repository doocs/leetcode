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
        dfs(nestedList);
    }

    int next() {
        return vals[cur++];
    }

    bool hasNext() {
        return cur < vals.size();
    }

private:
    vector<int> vals;
    int cur = 0;

    void dfs(vector<NestedInteger>& nestedList) {
        for (auto& e : nestedList) {
            if (e.isInteger()) {
                vals.push_back(e.getInteger());
            } else {
                dfs(e.getList());
            }
        }
    }
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */