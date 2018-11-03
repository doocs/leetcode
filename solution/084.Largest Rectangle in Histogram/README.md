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

其实是对第一种解法的一种补充解释，同样是构建升序栈

数学模型分析：
>假设题目中有升序水柱 {1,2,3,4,5,6}，构建成栈内元素stack = {1,2,3,4,5,6}
>
>假设第7个入栈元素（水柱）值为4时，栈顶元素6出栈，因为是首位出栈的元素，count = 1。表示高度为6的柱最大宽度是1，最大面积是ans = 6 * 1；
>
>stack={1,2,3,4,5};想入栈的元素还是刚才那个4,栈顶元素：5>4，所以5要出栈，因为是连续出栈的第二个元素，所以count++。表示高度为5的柱最大宽度是2，最大面积是ans = max(ans,5 * 2) = 10;
>
>stack={1,2,3,4};想入栈的还是那个4，现在栈顶元素:4=4了，直接入栈:stack={1,2,3,4,4}
>
>因为count=2,柱子数量应该和heights[i]下标i相同来补位,所以要进去两个数，即要补count个heights[i]
>
>stack={1,2,3,4,4,4,4}

1. 初始化一个空栈，下标`i = 0`，数组首位元素入栈，下标`i++`，出栈个数`count = 0`
2. 当heights[i]>=stack.top()，heights[i]入栈`i++,count =0`；否则，栈顶出栈，设置计数位count++,统计以栈顶元素出栈个数,`ans = max(ans,stack.top()*count)`,循环这一步操作
3. 若count>0,补全栈内元素；count=0不处理
4. 循环结束后计算升序栈以各元素为高的水柱面积


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