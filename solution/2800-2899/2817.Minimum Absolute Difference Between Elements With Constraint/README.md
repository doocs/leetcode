# [2817. 限制条件下元素之间的最小绝对差](https://leetcode.cn/problems/minimum-absolute-difference-between-elements-with-constraint)

[English Version](/solution/2800-2899/2817.Minimum%20Absolute%20Difference%20Between%20Elements%20With%20Constraint/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>x</code>&nbsp;。</p>

<p>请你找到数组中下标距离至少为 <code>x</code>&nbsp;的两个元素的 <strong>差值绝对值</strong>&nbsp;的 <strong>最小值</strong>&nbsp;。</p>

<p>换言之，请你找到两个下标&nbsp;<code>i</code> 和&nbsp;<code>j</code>&nbsp;，满足&nbsp;<code>abs(i - j) &gt;= x</code> 且&nbsp;<code>abs(nums[i] - nums[j])</code>&nbsp;的值最小。</p>

<p>请你返回一个整数，表示下标距离至少为 <code>x</code>&nbsp;的两个元素之间的差值绝对值的 <strong>最小值</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<b>输入：</b>nums = [4,3,2,4], x = 2
<b>输出：</b>0
<b>解释：</b>我们选择 nums[0] = 4 和 nums[3] = 4 。
它们下标距离满足至少为 2 ，差值绝对值为最小值 0 。
0 是最优解。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [5,3,2,10,15], x = 1
<b>输出：</b>1
<b>解释：</b>我们选择 nums[1] = 3 和 nums[2] = 2 。
它们下标距离满足至少为 1 ，差值绝对值为最小值 1 。
1 是最优解。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,4], x = 3
<b>输出：</b>3
<strong>解释：</strong>我们选择 nums[0] = 1 和 nums[3] = 4 。
它们下标距离满足至少为 3 ，差值绝对值为最小值 3 。
3 是最优解。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= x &lt; nums.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：有序集合**

我们创建一个有序集合，用于存储距离当前下标至少为 $x$ 的元素。

接下来，我们从下标 $i = x$ 开始枚举，每次将 $nums[i - x]$ 加入到有序集合中。然后找出有序集合中与 $nums[i]$ 最接近的两个元素，它们的差值绝对值的最小值就是答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
from sortedcontainers import SortedList


class Solution:
    def minAbsoluteDifference(self, nums: List[int], x: int) -> int:
        sl = SortedList()
        ans = inf
        for i in range(x, len(nums)):
            sl.add(nums[i - x])
            p = bisect_left(sl, nums[i])
            if p < len(sl):
                ans = min(ans, abs(nums[i] - sl[p]))
            if p:
                ans = min(ans, abs(nums[i] - sl[p - 1]))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minAbsoluteDifference(List<Integer> nums, int x) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int ans = 1 << 30;
        for (int i = x; i < nums.size(); ++i) {
            tm.merge(nums.get(i - x), 1, Integer::sum);
            Integer key = tm.ceilingKey(nums.get(i));
            if (key != null) {
                ans = Math.min(ans, key - nums.get(i));
            }
            key = tm.floorKey(nums.get(i));
            if (key != null) {
                ans = Math.min(ans, nums.get(i) - key);
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
    int minAbsoluteDifference(vector<int>& nums, int x) {
        int ans = 1 << 30;
        multiset<int> s;
        for (int i = x; i < nums.size(); ++i) {
            s.insert(nums[i - x]);
            auto it = s.lower_bound(nums[i]);
            if (it != s.end()) {
                ans = min(ans, *it - nums[i]);
            }
            if (it != s.begin()) {
                --it;
                ans = min(ans, nums[i] - *it);
            }
        }
        return ans;
    }
};
```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
