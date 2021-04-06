# [287. 寻找重复数](https://leetcode-cn.com/problems/find-the-duplicate-number)

[English Version](/solution/0200-0299/0287.Find%20the%20Duplicate%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个包含&nbsp;<em>n</em> + 1 个整数的数组&nbsp;<em>nums</em>，其数字都在 1 到 <em>n&nbsp;</em>之间（包括 1 和 <em>n</em>），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> <code>[1,3,4,2,2]</code>
<strong>输出:</strong> 2
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> [3,1,3,4,2]
<strong>输出:</strong> 3
</pre>

<p><strong>说明：</strong></p>

<ol>
	<li><strong>不能</strong>更改原数组（假设数组是只读的）。</li>
	<li>只能使用额外的 <em>O</em>(1) 的空间。</li>
	<li>时间复杂度小于 <em>O</em>(<em>n</em><sup>2</sup>) 。</li>
	<li>数组中只有一个重复的数字，但它可能不止重复出现一次。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二分法。

如果值范围在 `[1, mid]` 的数小于等于 mid，说明此范围内没有重复的数，否则说明有重复数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findDuplicate(self, nums: List[int]) -> int:
        l, r = 0, len(nums) - 1
        while l < r:
            mid = (l + r) >> 1
            cnt = 0
            for e in nums:
                if e <= mid:
                    cnt += 1
            if cnt <= mid:
                l = mid + 1
            else:
                r = mid
        return l
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findDuplicate(int[] nums) {
        int l = 1, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) >>> 1;
            int cnt = 0;
            for (int e : nums) {
                if (e <= mid) ++cnt;
            }
            if (cnt <= mid) l = mid + 1;
            else r = mid;
        }
        return l;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findDuplicate(vector<int>& nums) {
        int l = 0, r = nums.size() - 1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            int cnt = 0;
            for (auto e : nums) {
                if (e <= mid) ++cnt;
            }
            if (cnt <= mid) l = mid + 1;
            else r = mid;
        }
        return l;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
