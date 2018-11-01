## 颜色分类
### 题目描述

给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

注意:
不能使用代码库中的排序函数来解决这道题。

示例:
```
输入: [2,0,2,1,1,0]
输出: [0,0,1,1,2,2]
```

进阶：

- 一个直观的解决方案是使用计数排序的两趟扫描算法。
首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
- 你能想出一个仅使用常数空间的一趟扫描算法吗？

### 解法
指针 p 指示左侧等于 0 区域的最后一个元素，q 指示右侧等于 2 的第一个元素。p, q 初始分别指向 -1, nums.length。
cur 从 0 开始遍历：

- 若 nums[cur] == 0，则与 p 的下一个位置的元素互换，p+1， cur+1；
- 若 nums[cur] == 1，++cur；
- 若 nums[cur] == 2，则与 q 的前一个位置的元素互换，q-1，cur不变。

### 解法2

因为排序元素的类型有限而且不多，比较直观的方法是使用计数排序，就是创建一个包含所有类型的数组`（如创建数组count[3],count[0]=k表示类型是“0”的元素有k个）`，利用下标作为统计标识，在对应下标元素上+-

1. 创建一个含有3个元素的数组并初始化为0 `count[3] = {0}`
2. 遍历nums，在`count`数组对应数字下+1
3. 利用计数数组对nums重新赋值

#### Java(解法1)

```java
class Solution {
    public void sortColors(int[] nums) {
        int p = -1;
        int q = nums.length;
        int cur = 0;
        while (cur < q) {
            if (nums[cur] == 0) {
                swap(nums, cur++, ++p);
            } else if (nums[cur] == 1) {
                ++cur;
            } else {
                swap(nums, --q, cur);
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

#### CPP(解法2)

```
class Solution {
public:
    void sortColors(vector<int>& nums) {
        if(nums.empty())return ;
        
        int count[3] = {0};
        size_t len = nums.size();
        
        for(int i = 0;i<len;i++){
            count[nums[i]]++;
        }
        int index = 0;
        for(int i = 0;i<3;i++){
            while(count[i] != 0){
                nums[index++] = i;
                count[i]--;
            }
        }   
    }
};

```