class Solution {
public:
    int countElements(vector<int>& nums) {
        auto [mi, mx] = std::ranges::minmax_element(nums);
        return std::ranges::count_if(nums, [mi, mx](int x) {
            return *mi < x && x < *mx;
        });
    }
};
