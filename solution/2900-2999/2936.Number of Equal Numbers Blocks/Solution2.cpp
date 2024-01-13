/**
 * Definition for BigArray.
 * class BigArray {
 * public:
 *     BigArray(vector<int> elements);
 *     int at(long long index);
 *     long long size();
 * };
 */
class Solution {
public:
    int countBlocks(BigArray* nums) {
        using ll = long long;
        function<int(ll, ll)> f = [&](ll l, ll r) {
            if (nums->at(l) == nums->at(r)) {
                return 1;
            }
            ll mid = (l + r) >> 1;
            int a = f(l, mid);
            int b = f(mid + 1, r);
            return a + b - (nums->at(mid) == nums->at(mid + 1));
        };
        return f(0, nums->size() - 1);
    }
};