class Solution {
public:
    int searchInsert(vector<int>& nums, int target) {
        int len = nums.size();
        if(len == 0){
            nums.push_back(target);
            return 0;
        }

        auto iter = find(nums.begin(),nums.end(),target);
        
        if(iter != nums.end()){
           return binarySearch(nums,0,len - 1,target);
        }
        else{
            int slow = 0;
            int fast = 1;
            
            if(nums[0] >= target)return 0;
            if(nums[len-1] <= target)return len;
            
            while(fast < len){
                if(nums[slow] <= target && nums[fast] > target){
                    nums.insert(nums.begin() + fast,target);
                    return fast;
                }
                else{
                    slow++;
                    fast++;
                }
            }
            return fast;
        } 
    }
    
    
    int binarySearch(vector<int> &nums,int left,int right,int target){
        if(nums[left] == target)return left;
        if(nums[right] == target)return right;
        
        int mid = (left + right) / 2;
        
        if(nums[mid] > target)return binarySearch(nums,left+1,mid,target);
        else if(nums[mid] < target)return binarySearch(nums,mid,right-1,target);
        else return mid;
    }
    
};

-------------------------
class Solution {
public:
    int searchInsert(vector<int>& nums, int target) {
        if (nums.size() == 0) {
            nums.push_back(target);
            return 0;
        }
        unsigned int i = 0;
        
        while(i<nums.size()&&nums[i]<target)i++;

        nums.insert(nums.begin() + i, target);
        return i;
    }
};