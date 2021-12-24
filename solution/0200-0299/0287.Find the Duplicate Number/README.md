# [287. 寻找重复数](https://leetcode-cn.com/problems/find-the-duplicate-number)

[English Version](/solution/0200-0299/0287.Find%20the%20Duplicate%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个包含 <code>n + 1</code> 个整数的数组 <code>nums</code> ，其数字都在 <code>1</code> 到 <code>n</code><em> </em>之间（包括 <code>1</code> 和 <code>n</code>），可知至少存在一个重复的整数。</p>

<p>假设 <code>nums</code> 只有 <strong>一个重复的整数</strong> ，找出 <strong>这个重复的数</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,4,2,2]
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,1,3,4,2]
<strong>输出：</strong>3
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1]
<strong>输出：</strong>1
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,2]
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= n <= 3 * 10<sup>4</sup></code></li>
	<li><code>nums.length == n + 1</code></li>
	<li><code>1 <= nums[i] <= n</code></li>
	<li><code>nums</code> 中 <strong>只有一个整数</strong> 出现 <strong>两次或多次</strong> ，其余整数均只出现 <strong>一次</strong></li>
</ul>

<p> </p>

<p><b>进阶：</b></p>

<ul>
	<li>如何证明 <code>nums</code> 中至少存在一个重复的数字?</li>
	<li>你可以在不修改数组 <code>nums</code> 的情况下解决这个问题吗？</li>
	<li>你可以只用常量级 <code>O(1)</code> 的额外空间解决这个问题吗？</li>
	<li>你可以设计一个时间复杂度小于 <code>O(n<sup>2</sup>)</code> 的解决方案吗？</li>
</ul>

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
