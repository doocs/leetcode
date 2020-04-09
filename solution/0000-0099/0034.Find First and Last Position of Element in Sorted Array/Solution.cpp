class Solution {
public:
    bool binarySearch(vector<int> &nums,int &target,int &pos,int left,int right){
        int mid = left + ((right - left) >> 1);
        if(nums[left] == target){
            pos = left;
            return true;
        }
        if(nums[right] == target){
            pos = right;
            return true;
        }
        if(nums[mid] == target){
            pos = mid;
            return true;
        }
        
        if(left == mid){
            return false;
        }
        else if(nums[mid] > target){
            return binarySearch(nums,target,pos,left,mid-1);
        }
        else if(nums[mid] < target){
            return binarySearch(nums,target,pos,mid+1,right);
        } 
        
        return false;
    }
    
    vector<int> searchRange(vector<int>& nums, int target) {
        int pos = 0;
        int len = nums.size();
        if(len == 0)return {-1,-1};
        if(binarySearch(nums,target,pos,0,len-1)){
            int left = pos;
            int right = pos;
            while(left>0 && nums[left-1] == nums[pos])left--;
            while(right<len-1 && nums[right+1] == nums[pos])right++;
            return {left,right};
        }
        else{
            return {-1,-1};
        }
    }
};