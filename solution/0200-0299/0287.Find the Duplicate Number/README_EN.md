# [287. Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number)

[中文文档](/solution/0200-0299/0287.Find%20the%20Duplicate%20Number/README.md)

## Description

<p>Given an array <i>nums</i> containing <i>n</i> + 1 integers where each integer is between 1 and <i>n</i> (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.</p>

<p><b>Example 1:</b></p>

<pre>

<b>Input:</b> <code>[1,3,4,2,2]</code>

<b>Output:</b> 2

</pre>

<p><b>Example 2:</b></p>

<pre>

<b>Input:</b> [3,1,3,4,2]

<b>Output:</b> 3</pre>

<p><b>Note:</b></p>

<ol>
    <li>You <b>must not</b> modify the array (assume the array is read only).</li>
    <li>You must use only constant, <i>O</i>(1) extra space.</li>
    <li>Your runtime complexity should be less than <em>O</em>(<em>n</em><sup>2</sup>).</li>
    <li>There is only one duplicate number in the array, but it could be repeated more than once.</li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

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
