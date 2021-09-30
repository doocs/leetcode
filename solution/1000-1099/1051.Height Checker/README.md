# [1051. 高度检查器](https://leetcode-cn.com/problems/height-checker)

[English Version](/solution/1000-1099/1051.Height%20Checker/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>学校在拍年度纪念照时，一般要求学生按照 <strong>非递减</strong> 的高度顺序排列。</p>

<p>请你返回能让所有学生以 <strong>非递减</strong> 高度排列的最小必要移动人数。</p>

<p>注意，当一组学生被选中时，他们之间可以以任何可能的方式重新排序，而未被选中的学生应该保持不动。</p>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>heights = [1,1,4,2,1,3]
<strong>输出：</strong>3 
<strong>解释：</strong>
当前数组：[1,1,4,2,1,3]
目标数组：[1,1,1,2,3,4]
在下标 2 处（从 0 开始计数）出现 4 vs 1 ，所以我们必须移动这名学生。
在下标 4 处（从 0 开始计数）出现 1 vs 3 ，所以我们必须移动这名学生。
在下标 5 处（从 0 开始计数）出现 3 vs 4 ，所以我们必须移动这名学生。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>heights = [5,1,2,3,4]
<strong>输出：</strong>5
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>heights = [1,2,3,4,5]
<strong>输出：</strong>0
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= heights.length <= 100</code></li>
	<li><code>1 <= heights[i] <= 100</code></li>
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
