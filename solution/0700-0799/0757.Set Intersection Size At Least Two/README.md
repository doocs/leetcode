# [757. 设置交集大小至少为 2](https://leetcode.cn/problems/set-intersection-size-at-least-two)

[English Version](/solution/0700-0799/0757.Set%20Intersection%20Size%20At%20Least%20Two/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个整数区间&nbsp;<code>[a, b]</code>&nbsp;&nbsp;(&nbsp;<code>a &lt; b</code>&nbsp;) 代表着从&nbsp;<code>a</code>&nbsp;到&nbsp;<code>b</code>&nbsp;的所有连续整数，包括&nbsp;<code>a</code>&nbsp;和&nbsp;<code>b</code>。</p>

<p>给你一组整数区间<code>intervals</code>，请找到一个最小的集合 S，使得 S 里的元素与区间<code>intervals</code>中的每一个整数区间都至少有2个元素相交。</p>

<p>输出这个最小集合S的大小。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]
<strong>输出:</strong> 3
<strong>解释:</strong>
考虑集合 S = {2, 3, 4}. S与intervals中的四个区间都有至少2个相交的元素。
且这是S最小的情况，故我们输出3。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]
<strong>输出:</strong> 5
<strong>解释:</strong>
最小的集合S = {1, 2, 3, 4, 5}.
</pre>

<p><strong>注意:</strong></p>

<ol>
	<li><code>intervals</code>&nbsp;的长度范围为<code>[1, 3000]</code>。</li>
	<li><code>intervals[i]</code>&nbsp;长度为&nbsp;<code>2</code>，分别代表左、右边界。</li>
	<li><code>intervals[i][j]</code> 的值是&nbsp;<code>[0, 10^8]</code>范围内的整数。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 贪心**

相似题目： [452. 用最少数量的箭引爆气球](/solution/0400-0499/0452.Minimum%20Number%20of%20Arrows%20to%20Burst%20Balloons/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def intersectionSizeTwo(self, intervals: List[List[int]]) -> int:
        intervals.sort(key=lambda x: (x[1], -x[0]))
        s = e = -1
        ans = 0
        for a, b in intervals:
            if a <= s:
                continue
            if a > e:
                ans += 2
                s, e = b - 1, b
            else:
                ans += 1
                s, e = e, b
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]);
        int ans = 0;
        int s = -1, e = -1;
        for (int[] v : intervals) {
            int a = v[0], b = v[1];
            if (a <= s) {
                continue;
            }
            if (a > e) {
                ans += 2;
                s = b - 1;
                e = b;
            } else {
                ans += 1;
                s = e;
                e = b;
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
    int intersectionSizeTwo(vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end(), [&](vector<int>& a, vector<int>& b) {
            return a[1] == b[1] ? a[0] > b[0] : a[1] < b[1];
        });
        int ans = 0;
        int s = -1, e = -1;
        for (auto& v : intervals) {
            int a = v[0], b = v[1];
            if (a <= s) continue;
            if (a > e) {
                ans += 2;
                s = b - 1;
                e = b;
            } else {
                ans += 1;
                s = e;
                e = b;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func intersectionSizeTwo(intervals [][]int) int {
    sort.Slice(intervals, func(i, j int) bool {
        a, b := intervals[i], intervals[j]
        if a[1] == b[1] {
            return a[0] > b[0]
        }
        return a[1] < b[1]
    })
    ans := 0
    s, e := -1, -1
    for _, v := range intervals {
        a, b := v[0], v[1]
        if a <= s {
            continue
        }
        if a > e {
            ans += 2
            s, e = b - 1, b
        } else {
            ans += 1
            s, e = e, b
        }
    }
    return ans
}
```

### **...**

```

```

<!-- tabs:end -->
