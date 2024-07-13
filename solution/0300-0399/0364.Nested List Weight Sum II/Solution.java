/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    private int maxDepth;
    private int ws;
    private int s;

    public int depthSumInverse(List<NestedInteger> nestedList) {
        for (NestedInteger x : nestedList) {
            dfs(x, 1);
        }
        return (maxDepth + 1) * s - ws;
    }

    private void dfs(NestedInteger x, int d) {
        maxDepth = Math.max(maxDepth, d);
        if (x.isInteger()) {
            ws += x.getInteger() * d;
            s += x.getInteger();
        } else {
            for (NestedInteger y : x.getList()) {
                dfs(y, d + 1);
            }
        }
    }
}