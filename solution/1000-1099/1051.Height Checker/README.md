# [1051. 高度检查器](https://leetcode-cn.com/problems/height-checker)

[English Version](/solution/1000-1099/1051.Height%20Checker/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>学校打算为全体学生拍一张年度纪念照。根据要求，学生需要按照 <strong>非递减</strong> 的高度顺序排成一行。</p>

<p>排序后的高度情况用整数数组 <code>expected</code> 表示，其中 <code>expected[i]</code> 是预计排在这一行中第 <code>i</code> 位的学生的高度（<strong>下标从 0 开始</strong>）。</p>

<p>给你一个整数数组 <code>heights</code> ，表示 <strong>当前学生站位</strong> 的高度情况。<code>heights[i]</code> 是这一行中第 <code>i</code> 位学生的高度（<strong>下标从 0 开始</strong>）。</p>

<p>返回满足<em> </em><code>heights[i] != expected[i]</code> 的 <strong>下标数量</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>heights =&nbsp;[1,1,4,2,1,3]
<strong>输出：</strong>3 
<strong>解释：</strong>
高度：[1,1,<em><strong>4</strong></em>,2,<em><strong>1</strong></em>,<em><strong>3</strong></em>]
预期：[1,1,<em><strong>1</strong></em>,2,<em><strong>3</strong></em>,<em><strong>4</strong></em>]
下标 2 、4 、5 处的学生高度不匹配。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>heights = [5,1,2,3,4]
<strong>输出：</strong>5
<strong>解释：</strong>
高度：[<em><strong>5</strong></em>,<em><strong>1</strong></em>,<em><strong>2</strong></em>,<em><strong>3</strong></em>,<em><strong>4</strong></em>]
预期：[<em><strong>1</strong></em>,<em><strong>2</strong></em>,<em><strong>3</strong></em>,<em><strong>4</strong></em>,<em><strong>5</strong></em>]
所有下标的对应学生高度都不匹配。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>heights = [1,2,3,4,5]
<strong>输出：</strong>0
<strong>解释：</strong>
高度：[1,2,3,4,5]
预期：[1,2,3,4,5]
所有下标的对应学生高度都匹配。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= heights.length &lt;= 100</code></li>
	<li><code>1 &lt;= heights[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def heightChecker(self, heights: List[int]) -> int:
        bucket = [0] * 101
        for h in heights:
            bucket[h] += 1
        res = i = 0
        for j in range(1, 101):
            while bucket[j] > 0:
                bucket[j] -= 1
                if heights[i] != j:
                    res += 1
                i += 1
        return res
```

```python
class Solution:
    def heightChecker(self, heights: List[int]) -> int:
        expected = sorted(heights)
        return sum(1 for i, h in enumerate(heights) if h != expected[i])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int heightChecker(int[] heights) {
        int[] expected = Arrays.copyOf(heights, heights.length);
        Arrays.sort(expected);
        int res = 0;
        for (int i = 0; i < heights.length; ++i) {
            if (heights[i] != expected[i]) {
                ++res;
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int heightChecker(vector<int>& heights) {
        vector<int> expected = heights;
        sort(expected.begin(), expected.end());
        int res = 0;
        for (int i = 0; i < heights.size(); ++i)
        {
            if (heights[i] != expected[i]) ++res;
        }
        return res;
    }
};
```

### **Go**

```go
func heightChecker(heights []int) int {
    expected := make([]int, len(heights))
    copy(expected, heights)
    sort.Ints(expected)
    res := 0
    for i, h := range heights {
        if h != expected[i] {
            res++
        }
    }
    return res
}
```

### **...**

```

```

<!-- tabs:end -->
