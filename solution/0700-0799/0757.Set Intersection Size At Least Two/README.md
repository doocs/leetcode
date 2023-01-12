# [757. 设置交集大小至少为 2](https://leetcode.cn/problems/set-intersection-size-at-least-two)

[English Version](/solution/0700-0799/0757.Set%20Intersection%20Size%20At%20Least%20Two/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维整数数组 <code>intervals</code> ，其中 <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> 表示从 <code>start<sub>i</sub></code> 到 <code>end<sub>i</sub></code> 的所有整数，包括 <code>start<sub>i</sub></code> 和 <code>end<sub>i</sub></code> 。</p>

<p><strong>包含集合</strong> 是一个名为 <code>nums</code> 的数组，并满足 <code>intervals</code> 中的每个区间都 <strong>至少</strong> 有 <strong>两个</strong> 整数在 <code>nums</code> 中。</p>

<ul>
	<li>例如，如果 <code>intervals = [[1,3], [3,7], [8,9]]</code> ，那么 <code>[1,2,4,7,8,9]</code> 和 <code>[2,3,4,8,9]</code> 都符合 <strong>包含集合</strong> 的定义。</li>
</ul>

<p>返回包含集合可能的最小大小。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,3],[3,7],[8,9]]
<strong>输出：</strong>5
<strong>解释：</strong>nums = [2, 3, 4, 8, 9].
可以证明不存在元素数量为 4 的包含集合。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,3],[1,4],[2,5],[3,5]]
<strong>输出：</strong>3
<strong>解释：</strong>nums = [2, 3, 4].
可以证明不存在元素数量为 2 的包含集合。 
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,2],[2,3],[2,4],[4,5]]
<strong>输出：</strong>5
<strong>解释：</strong>nums = [1, 2, 3, 4, 5].
可以证明不存在元素数量为 4 的包含集合。 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 3000</code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt; end<sub>i</sub> &lt;= 10<sup>8</sup></code></li>
</ul>

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
