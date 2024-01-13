/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * class NestedInteger {
 *   public:
 *     // Constructor initializes an empty nested list.
 *     NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     NestedInteger(int value);
 *
 *     // Return true if this NestedInteger holds a single integer, rather than a nested list.
 *     bool isInteger() const;
 *
 *     // Return the single integer that this NestedInteger holds, if it holds a single integer
 *     // The result is undefined if this NestedInteger holds a nested list
 *     int getInteger() const;
 *
 *     // Set this NestedInteger to hold a single integer.
 *     void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     void add(const NestedInteger &ni);
 *
 *     // Return the nested list that this NestedInteger holds, if it holds a nested list
 *     // The result is undefined if this NestedInteger holds a single integer
 *     const vector<NestedInteger> &getList() const;
 * };
 */
class Solution {
public:
    NestedInteger deserialize(string s) {
        if (s[0] != '[') {
            return NestedInteger(stoi(s));
        }
        stack<NestedInteger> stk;
        int x = 0;
        bool neg = false;
        for (int i = 0; i < s.size(); ++i) {
            if (s[i] == '-') {
                neg = true;
            } else if (isdigit(s[i])) {
                x = x * 10 + s[i] - '0';
            } else if (s[i] == '[') {
                stk.push(NestedInteger());
            } else if (s[i] == ',' || s[i] == ']') {
                if (isdigit(s[i - 1])) {
                    if (neg) {
                        x = -x;
                    }
                    stk.top().add(NestedInteger(x));
                }
                x = 0;
                neg = false;
                if (s[i] == ']' && stk.size() > 1) {
                    auto t = stk.top();
                    stk.pop();
                    stk.top().add(t);
                }
            }
        }
        return stk.top();
    }
};