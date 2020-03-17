class Solution {
public:
    int search(vector<int>& nums, int target) {
        int len = nums.size();
        int left = 0;
        int right = len - 1;
        int mid;
        while(left <= right) {
            mid = (left + right) / 2;
            if(nums[mid] == target)return mid;
            
            if(nums[mid] < nums[right]) {
                if(nums[right] >= target && nums[mid] < target)left = mid + 1;
                else right = mid - 1;
            }
            else {
                if(nums[left] <= target && nums[mid] > target)right = mid - 1;
                else left = mid + 1;
            }
        }
        return -1;    
    }
};

---------------
class Solution {
public:
    int search(vector<int>& nums, int target) {
        int len = nums.size();
        for(int i = 0;i<len;i++) {
            if(target == nums[i])return i;
        }
        
        return -1;
    }
};
