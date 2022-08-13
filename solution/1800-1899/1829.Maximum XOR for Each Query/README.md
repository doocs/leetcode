# [1829. 每个查询的最大异或值](https://leetcode.cn/problems/maximum-xor-for-each-query)

[English Version](/solution/1800-1899/1829.Maximum%20XOR%20for%20Each%20Query/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>有序</strong> 数组 <code>nums</code> ，它由 <code>n</code> 个非负整数组成，同时给你一个整数 <code>maximumBit</code> 。你需要执行以下查询 <code>n</code> 次：</p>

<ol>
	<li>找到一个非负整数 <code>k < 2<sup>maximumBit</sup></code> ，使得 <code>nums[0] XOR nums[1] XOR ... XOR nums[nums.length-1] XOR k</code> 的结果 <strong>最大化</strong> 。<code>k</code> 是第 <code>i</code> 个查询的答案。</li>
	<li>从当前数组 <code>nums</code> 删除 <strong>最后</strong> 一个元素。</li>
</ol>

<p>请你返回一个数组 <code>answer</code> ，其中<em> </em><code>answer[i]</code>是第 <code>i</code> 个查询的结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [0,1,1,3], maximumBit = 2
<b>输出：</b>[0,3,2,3]
<b>解释：</b>查询的答案如下：
第一个查询：nums = [0,1,1,3]，k = 0，因为 0 XOR 1 XOR 1 XOR 3 XOR 0 = 3 。
第二个查询：nums = [0,1,1]，k = 3，因为 0 XOR 1 XOR 1 XOR 3 = 3 。
第三个查询：nums = [0,1]，k = 2，因为 0 XOR 1 XOR 2 = 3 。
第四个查询：nums = [0]，k = 3，因为 0 XOR 3 = 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,3,4,7], maximumBit = 3
<b>输出：</b>[5,2,6,5]
<b>解释：</b>查询的答案如下：
第一个查询：nums = [2,3,4,7]，k = 5，因为 2 XOR 3 XOR 4 XOR 7 XOR 5 = 7。
第二个查询：nums = [2,3,4]，k = 2，因为 2 XOR 3 XOR 4 XOR 2 = 7 。
第三个查询：nums = [2,3]，k = 6，因为 2 XOR 3 XOR 6 = 7 。
第四个查询：nums = [2]，k = 5，因为 2 XOR 5 = 7 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [0,1,2,2,5,7], maximumBit = 3
<b>输出：</b>[4,3,6,4,6,7]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums.length == n</code></li>
	<li><code>1 <= n <= 10<sup>5</sup></code></li>
	<li><code>1 <= maximumBit <= 20</code></li>
	<li><code>0 <= nums[i] < 2<sup>maximumBit</sup></code></li>
	<li><code>nums</code>​​​ 中的数字已经按 <strong>升序</strong> 排好序。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

前缀异或。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getMaximumXor(self, nums: List[int], maximumBit: int) -> List[int]:
        n = len(nums)
        s = [0] * (n + 1)
        for i, x in enumerate(nums):
            s[i + 1] = s[i] ^ x
        ans = []
        for x in s[:0:-1]:
            t = 0
            for i in range(maximumBit):
                if ((x >> i) & 1) == 0:
                    t |= 1 << i
            ans.append(t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] ^ nums[i];
        }
        int[] ans = new int[n];
        for (int i = n; i > 0; --i) {
            int t = 0, x = s[i];
            for (int j = 0; j < maximumBit; ++j) {
                if (((x >> j) & 1) == 0) {
                    t |= (1 << j);
                }
            }
            ans[n - i] = t;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> getMaximumXor(vector<int>& nums, int maximumBit) {
        int n = nums.size();
        vector<int> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] ^ nums[i];
        vector<int> ans;
        for (int i = n; i > 0; --i) {
            int t = 0, x = s[i];
            for (int j = 0; j < maximumBit; ++j) {
                if (((x >> j) & 1) == 0) t |= (1 << j);
            }
            ans.push_back(t);
        }
        return ans;
    }
};
```

### **Go**

```go
func getMaximumXor(nums []int, maximumBit int) []int {
	n := len(nums)
	s := make([]int, n+1)
	for i, v := range nums {
		s[i+1] = s[i] ^ v
	}
	var ans []int
	for i := n; i > 0; i-- {
		t, x := 0, s[i]
		for j := 0; j < maximumBit; j++ {
			if ((x >> j) & 1) == 0 {
				t |= (1 << j)
			}
		}
		ans = append(ans, t)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
