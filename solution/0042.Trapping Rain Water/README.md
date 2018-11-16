## 接雨水
### 问题描述

给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

![ ](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/rainwatertrap.png)

上面是由数组`[0,1,0,2,1,0,1,3,2,1,2,1]` 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。

示例:
```
输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6
```

### 思路

方法是找凹槽，怎么找呢？

1. 设置`slow,fast`两个下标代表凹槽的左右边界，一旦遇到`height[fast]>=height[slow]`的情况，计算凹槽的容积
2. 上面情况是以右边界高度一定大于左边界为准的，当形成凹槽且左边界大于右边界时，要怎么记录呢？答案是设置`stopPoint`点，规则是当`height[fast]>height[stopPoint]`时有`stopPoint = fast`记录右边最高点；同时当fast越界时，会到`stopPoint`上

```CPP
class Solution {
public:
    int trap(vector<int>& height) {
        int len = height.size();
        if(len == 0 || len == 1)return 0;
        
        int slow = 0;
        int fast = 1;
        
        int stopPoint;
        
        int total = 0;
        int bottom;
        int tmp = 0;
        while(fast < len){
            //每次更新stopPoint
            stopPoint = fast;
            while(fast < len && height[fast] <= height[slow]){
                if(height[fast] > height[stopPoint])
                    stopPoint = fast;
                fast++;
            }
            
            //越界了要回到stopPoint
            if(fast >= len)fast = stopPoint;

            tmp = 0;
            bottom = min(height[slow],height[fast]);
            for(int i = slow+1;i<fast;i++){
                tmp += bottom - height[i];
            }
            slow = fast;
            total += tmp;
            fast++;
        }
        return total;    
    }  
};
```