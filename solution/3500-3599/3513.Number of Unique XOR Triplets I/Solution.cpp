class Solution {
public:
    int uniqueXorTriplets(vector<int>& nums) {
        size_t n = nums.size();
        return n <= 2 ? n : 1 << bit_width(n);
    }
};