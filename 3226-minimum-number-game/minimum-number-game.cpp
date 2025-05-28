class Solution {
public:
    vector<int> numberGame(vector<int>& nums) {
        vector<int> arr;
         sort(nums.begin() , nums.end());
        for(int i=1; i<nums.size(); i=i+2){
           arr.push_back(nums[i]);
           arr.push_back(nums[i-1]);
        }
        return arr;
    }
};