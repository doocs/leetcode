# [594. 最长和谐子序列](https://leetcode.cn/problems/longest-harmonious-subsequence)

[English Version](/solution/0500-0599/0594.Longest%20Harmonious%20Subsequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>和谐数组是指一个数组里元素的最大值和最小值之间的差别 <strong>正好是 <code>1</code></strong> 。</p>

<p>现在，给你一个整数数组 <code>nums</code> ，请你在所有可能的子序列中找到最长的和谐子序列的长度。</p>

<p>数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,2,2,5,2,3,7]
<strong>输出：</strong>5
<strong>解释：</strong>最长的和谐子序列是 [3,2,2,2,3]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4]
<strong>输出：</strong>2
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1,1]
<strong>输出：</strong>0
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 2 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> <= nums[i] <= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先用哈希表统计每个元素出现的次数。然后遍历数组，判断比每个元素 `num` 大 1 的数字 `num + 1` 是否在哈希表中，若是，累计 `num` 和 `num + 1` 出现的次数，与最大值 ans 比较。若更大，则替换。最后返回 ans 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findLHS(self, nums: List[int]) -> int:
        ans = 0
        counter = Counter(nums)
        for num in nums:
            if num + 1 in counter:
                ans = max(ans, counter[num] + counter[num + 1])
        return ans
```

```python
class Solution:
    def findLHS(self, nums: List[int]) -> int:
        counter = Counter(nums)
        return max([counter[num] + counter[num + 1] for num in nums if num + 1 in counter], default=0)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (int num : nums) {
            if (counter.containsKey(num + 1)) {
                ans = Math.max(ans, counter.get(num) + counter.get(num + 1));
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
    int findLHS(vector<int>& nums) {
        unordered_map<int, int> counter;
        for (int num : nums) {
            ++counter[num];
        }
        int ans = 0;
        for (int num : nums) {
            if (counter.count(num + 1)) {
                ans = max(ans, counter[num] + counter[num + 1]);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findLHS(nums []int) int {
	counter := make(map[int]int)
	for _, num := range nums {
		counter[num]++
	}
	ans := 0
	for _, num := range nums {
		if counter[num+1] > 0 {
			ans = max(ans, counter[num]+counter[num+1])
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
