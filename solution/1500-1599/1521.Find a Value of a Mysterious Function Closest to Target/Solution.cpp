class Solution {
public:
    int closestToTarget(vector<int>& arr, int target) {
        int ans = abs(arr[0] - target);
        unordered_set<int> pre;
        pre.insert(arr[0]);
        for (int x : arr) {
            unordered_set<int> cur;
            cur.insert(x);
            for (int y : pre) {
                cur.insert(x & y);
            }
            for (int y : cur) {
                ans = min(ans, abs(y - target));
            }
            pre = move(cur);
        }
        return ans;
    }
};