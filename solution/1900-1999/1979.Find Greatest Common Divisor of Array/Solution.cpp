class Solution {
public:
    int findGCD(vector<int>& nums) {
        int a = *max_element(nums.begin(), nums.end());
        int b = *min_element(nums.begin(), nums.end());
        return gcd(a, b);
    }
};