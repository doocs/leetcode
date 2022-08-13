# [1402. 做菜顺序](https://leetcode.cn/problems/reducing-dishes)

[English Version](/solution/1400-1499/1402.Reducing%20Dishes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个厨师收集了他&nbsp;<code>n</code>&nbsp;道菜的满意程度&nbsp;<code>satisfaction</code>&nbsp;，这个厨师做出每道菜的时间都是 1 单位时间。</p>

<p>一道菜的 「喜爱时间」系数定义为烹饪这道菜以及之前每道菜所花费的时间乘以这道菜的满意程度，也就是&nbsp;<code>time[i]</code>*<code>satisfaction[i]</code>&nbsp;。</p>

<p>请你返回做完所有菜 「喜爱时间」总和的最大值为多少。</p>

<p>你可以按&nbsp;<strong>任意</strong>&nbsp;顺序安排做菜的顺序，你也可以选择放弃做某些菜来获得更大的总和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>satisfaction = [-1,-8,0,5,-9]
<strong>输出：</strong>14
<strong>解释：</strong>去掉第二道和最后一道菜，最大的喜爱时间系数和为 (-1*1 + 0*2 + 5*3 = 14) 。每道菜都需要花费 1 单位时间完成。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>satisfaction = [4,3,2]
<strong>输出：</strong>20
<strong>解释：</strong>按照原来顺序相反的时间做菜 (2*1 + 3*2 + 4*3 = 20)
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>satisfaction = [-1,-4,-5]
<strong>输出：</strong>0
<strong>解释：</strong>大家都不喜欢这些菜，所以不做任何菜可以获得最大的喜爱时间系数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == satisfaction.length</code></li>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>-1000 &lt;= satisfaction[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 贪心**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxSatisfaction(self, satisfaction: List[int]) -> int:
        satisfaction.sort(reverse=True)
        ans = presum = 0
        for v in satisfaction:
            presum += v
            if presum > 0:
                ans += presum
            else:
                break
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int ans = 0, presum = 0;
        for (int i = satisfaction.length - 1; i >= 0; --i) {
            presum += satisfaction[i];
            if (presum > 0) {
                ans += presum;
            } else {
                break;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxSatisfaction(vector<int>& satisfaction) {
        sort(rbegin(satisfaction), rend(satisfaction));
        int ans = 0, presum = 0;
        for (int v : satisfaction) {
            presum += v;
            if (presum > 0)
                ans += presum;
            else
                break;
        }
        return ans;
    }
};
```

### **Go**

```go
func maxSatisfaction(satisfaction []int) int {
	sort.Ints(satisfaction)
	ans, presum := 0, 0
	for i := len(satisfaction) - 1; i >= 0; i-- {
		presum += satisfaction[i]
		if presum > 0 {
			ans += presum
		} else {
			break
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
