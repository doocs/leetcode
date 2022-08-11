# [2229. 检查数组是否连贯](https://leetcode.cn/problems/check-if-an-array-is-consecutive)

[English Version](/solution/2200-2299/2229.Check%20if%20an%20Array%20Is%20Consecutive/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，如果 <code>nums</code> 是一个 <strong>连贯数组</strong> ，则返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p><span style="">如果数组包含 </span><code>[x, x + n - 1]</code><span style=""> 范围内的所有数字（包括 <code>x</code> 和 <code>x + n - 1</code> ），则该数组为连贯数组；其中</span> <code>x</code><span style=""> 是数组中最小的数，</span><code>n</code> <span style="">是数组的长度。</span></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,4,2]
<strong>输出：</strong>true
<strong>解释：</strong>
最小值是 1 ，数组长度为 4 。
范围 [x, x + n - 1] 中的所有值都出现在 nums 中：[1, 1 + 4 - 1] = [1, 4] = (1, 2, 3, 4) 。
因此，nums 是一个连贯数组。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3]
<strong>输出：</strong>false
<strong>解释：
</strong>最小值是 1 ，数组长度为 2 。 
范围 [x, x + n - 1] 中的所有值没有都出现在 nums 中：[1, 1 + 2 - 1] = [1, 2] = (1, 2) 。 
因此，nums 不是一个连贯数组。 
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,5,4]
<strong>输出：</strong>true
<strong>解释：</strong>
最小值是 3 ，数组长度为 3 。
范围 [x, x + n - 1] 中的所有值都出现在 nums 中：[3, 3 + 3 - 1] = [3, 5] = (3，4，5) 。
因此，nums 是一个连贯数组。
</pre>

<p>&nbsp;</p>
<strong>提示：</strong>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isConsecutive(self, nums: List[int]) -> bool:
        mi, mx = min(nums), max(nums)
        n = len(nums)
        return len(set(nums)) == n and mx == mi + n - 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isConsecutive(int[] nums) {
        int mi = nums[0];
        int mx = nums[0];
        Set<Integer> s = new HashSet<>();
        for (int v : nums) {
            mi = Math.min(mi, v);
            mx = Math.max(mx, v);
            s.add(v);
        }
        int n = nums.length;
        return s.size() == n && mx == mi + n - 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isConsecutive(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        int mi = *min_element(nums.begin(), nums.end());
        int mx = *max_element(nums.begin(), nums.end());
        int n = nums.size();
        return s.size() == n && mx == mi + n - 1;
    }
};
```

### **Go**

```go
func isConsecutive(nums []int) bool {
	s := make(map[int]bool)
	mi, mx := nums[0], nums[0]
	for _, v := range nums {
		s[v] = true
		mi = min(mi, v)
		mx = max(mx, v)
	}
	return len(s) == len(nums) && mx == mi+len(nums)-1
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
