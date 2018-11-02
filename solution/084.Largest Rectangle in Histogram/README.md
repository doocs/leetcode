## 柱状图中最大的矩形
### 题目描述

给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。

![histogram](http://p9ucdlghd.bkt.clouddn.com/histogram.png)

以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 `[2,1,5,6,2,3]`。

图中阴影部分为所能勾勒出的最大矩形面积，其面积为 `10` 个单位。

![histogram_area](http://p9ucdlghd.bkt.clouddn.com/histogram_area.png)
 
示例:
```
输入: [2,1,5,6,2,3]
输出: 10
```

### 解法1
从前往后遍历 heightss[0...n]：

- 若 heightss[i] > heightss[i - 1]，则将 i 压入栈中；
- 若 heightss[i] <= heightss[i - 1]，则依次弹出栈，计算栈中能得到的最大矩形面积。

注意，压入栈中的是柱子的索引，而非柱子的高度。（通过索引可以获得高度、距离差）

### 解法2:构建升序栈

[见原文(方法二)](https://blog.csdn.net/jingsuwen1/article/details/51577983)

**1、如果已知height数组是升序的，应该怎么做？**

>比如1,2,5,7,8
>
>那么就是(1\*5) vs. (2\*4) vs. (5\*3) vs. (7\*2) vs. (8\*1)
>
>也就是max(height[i]*(size-i))


**2、使用栈的目的就是构造这样的升序序列，按照以上方法求解。**


>但是height本身不一定是升序的，应该怎样构建栈？
>
>比如2,1,5,6,2,3
>
>（1）2进栈。s={2}, result = 0
>
>（2）1比2小，不满足升序条件，因此将2弹出，并记录当前结果为2*1=2。
>
>将2替换为1重新进栈。s={1,1}, result = 2
>
>（3）5比1大，满足升序条件，进栈。s={1,1,5},result = 2
>
>（4）6比5大，满足升序条件，进栈。s={1,1,5,6},result = 2
>
>（5）2比6小，不满足升序条件，因此将6弹出，并记录当前结果为6*1=6。s={1,1,5},result = 6
>
>       2比5小，不满足升序条件，因此将5弹出，并记录当前结果为5*2=10（因为已经弹出的5,6是升序的）。s={1,1},result = 10
>
>       2比1大，将弹出的5,6替换为2重新进栈。s={1,1,2,2,2},result = 10
>
>（6）3比2大，满足升序条件，进栈。s={1,1,2,2,2,3},result = 10
>
>栈构建完成，满足升序条件，因此按照升序处理办法得到上述的max(height[i]\*(size-i))=max{3\*1, 2\*2,2\*3, 2\*4, 1\*5, 1\*6}=8<10
>
>综上所述，result=10

------------------------------
### JAVA(解法1)

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        
        int n = heights.length;
        if (n == 1) {
            return heights[0];
        }
        
        // 创建一个新的数组，数组长度为 n + 1，最后一个元素值赋为 0
        // 确保在后面的遍历中，原数组最后一个元素值能得到计算
        int[] heightss = new int[n + 1];
        heightss[n] = 0;
        for (int i = 0; i < n; ++i) {
            heightss[i] = heights[i];
        }
        
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        
        for (int i =  0; i <= n;) {
            if (stack.isEmpty() || heightss[i] > heightss[stack.peek()]) {
                stack.push(i++);
            } else {
                int index = stack.pop();
                max = Math.max(max, heightss[index] * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
        }
        
        return max;
        
        
    }
}
```

### CPP(解法2)

```CPP
class Solution {
public:
    int largestRectangleArea(vector<int>& heights) {
        if(heights.empty())return 0;
        int len = heights.size();
        
        stack<int> s_stack;
        int ans = 0;
        
        for(int i = 0;i < len;i++){
            if(s_stack.empty() || heights[i] >= s_stack.top())
            {//满足升序条件
                s_stack.push(heights[i]);
            }
            else
            {//不满足升序
                int count = 0;
                while(!s_stack.empty() && s_stack.top() > heights[i])
                {
                    count++;
                    ans = max(ans,s_stack.top() * count);
                    s_stack.pop();
                }
                while(count > 0)
                {
                    s_stack.push(heights[i]);
                    count--;
                }
                s_stack.push(heights[i]);
            }
        }
        
        int count = 1;
        while(!s_stack.empty()){
            ans = max(ans,s_stack.top() * count);
            s_stack.pop();
            count++;
        }
        
        return ans;
    }
};
```