## 三数之和
### 题目描述

给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。
```
例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]
```

### 解法
先对数组进行排序，遍历数组，固定第一个数i。利用两个指针 p, q 分别指示 i+1, n-1。如果三数之和为0，移动 p, q；如果大于 0，左移 q；如果小于 0，右移 p。遍历到 nums[i] > 0 时，退出循环。

还要注意过滤重复元素。

#### Java

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        if (n < 3) {
            return list;
        }
        int p = 0;
        int q = 0;
        for (int i = 0; i < n - 2; ++i) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            p = i + 1;
            q = n - 1;
            
            while (p < q) {
                int val = nums[p] + nums[q] + nums[i];
                if (val == 0) {
                    list.add(Arrays.asList(nums[i], nums[p], nums[q]));
                    ++p;
                    while (p < q && nums[p] == nums[p - 1]) {
                        ++p;
                    }
                    --q;
                    while (p < q && nums[q] == nums[q + 1]) {
                        --q;
                    }
                    
                } else {
                    q = val > 0 ? q - 1 : q;
                    p = val < 0 ? p + 1 : p;
                }
                
            }  
        }
        return list;
        
    }
    
}
```

#### CPP

```CPP
class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        sort(nums.begin(),nums.end());
        
        vector<vector<int>> ans;
        
        int sum;
        int len = nums.size();
        int left,right;
        for(int i = 0; i< len;i++){
            left = i + 1;
            right = len - 1;
            while(left < right){
                sum = nums[i] + nums[left] + nums[right];
                if(sum == 0){
                    vector<int> vec;
                    vec.push_back(nums[i]);
                    vec.push_back(nums[left]);
                    vec.push_back(nums[right]);
                    ans.push_back(vec);
                    
                    while(left < right && nums[left] == nums[left + 1])left++;
                    while(left < right && nums[right] == nums[right - 1])right--;
                    
                    left++;
                    right--;
                    
                }
                if(sum > 0)right--;
                if(sum < 0)left++;
            }
            
            while(i<len-1 && nums[i] == nums[i+1])i++;
        }
        
        return ans;
        
    }
};
```