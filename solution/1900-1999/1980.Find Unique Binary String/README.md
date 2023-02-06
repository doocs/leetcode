# [1980. 找出不同的二进制字符串](https://leetcode.cn/problems/find-unique-binary-string)

[English Version](/solution/1900-1999/1980.Find%20Unique%20Binary%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串数组 <code>nums</code> ，该数组由 <code>n</code> 个 <strong>互不相同</strong> 的二进制字符串组成，且每个字符串长度都是 <code>n</code> 。请你找出并返回一个长度为&nbsp;<code>n</code>&nbsp;且&nbsp;<strong>没有出现</strong> 在 <code>nums</code> 中的二进制字符串<em>。</em>如果存在多种答案，只需返回 <strong>任意一个</strong> 即可。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = ["01","10"]
<strong>输出：</strong>"11"
<strong>解释：</strong>"11" 没有出现在 nums 中。"00" 也是正确答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = ["00","01"]
<strong>输出：</strong>"11"
<strong>解释：</strong>"11" 没有出现在 nums 中。"10" 也是正确答案。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = ["111","011","001"]
<strong>输出：</strong>"101"
<strong>解释：</strong>"101" 没有出现在 nums 中。"000"、"010"、"100"、"110" 也是正确答案。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 16</code></li>
	<li><code>nums[i].length == n</code></li>
	<li><code>nums[i] </code>为 <code>'0'</code> 或 <code>'1'</code></li>
	<li><code>nums</code> 中的所有字符串 <strong>互不相同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数 + 枚举**

由于 `'1'` 在长度为 $n$ 的二进制字符串中出现的次数可以为 $0, 1, 2, \cdots, n$（共有 $n + 1$ 种可能），因此我们一定可以找出一个新的二进制字符串，满足 `'1'` 在字符串中出现次数与 `nums` 中每个字符串不同。

我们可以用一个整数 $mask$ 记录所有字符串中 `'1'` 出现次数的情况，即 $mask$ 的第 $i$ 位为 $1$ 表示长度为 $n$ 的二进制字符串中 `'1'` 出现次数为 $i$ 的字符串存在，否则不存在。

然后我们从 $0$ 开始枚举长度为 $n$ 的二进制字符串中 `'1'` 出现的次数 $i$，如果 $mask$ 的第 $i$ 位为 $0$，则说明长度为 $n$ 的二进制字符串中 `'1'` 出现次数为 $i$ 的字符串不存在，我们可以将这个字符串作为答案返回。

时间复杂度 $O(L)$，空间复杂度 $O(1)$。其中 $L$ 为 `nums` 中字符串的总长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findDifferentBinaryString(self, nums: List[str]) -> str:
        mask = 0
        for x in nums:
            mask |= 1 << x.count("1")
        n = len(nums)
        for i in range(n + 1):
            if mask >> i & 1 ^ 1:
                return "1" * i + "0" * (n - i)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String findDifferentBinaryString(String[] nums) {
        int mask = 0;
        for (var x : nums) {
            int cnt = 0;
            for (int i = 0; i < x.length(); ++i) {
                if (x.charAt(i) == '1') {
                    ++cnt;
                }
            }
            mask |= 1 << cnt;
        }
        for (int i = 0;; ++i) {
            if ((mask >> i & 1) == 0) {
                return "1".repeat(i) + "0".repeat(nums.length - i);
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string findDifferentBinaryString(vector<string>& nums) {
        int mask = 0;
        for (auto& x : nums) {
            int cnt = count(x.begin(), x.end(), '1');
            mask |= 1 << cnt;
        }
        for (int i = 0; ; ++i) {
            if (mask >> i & 1 ^ 1) {
                return string(i, '1') + string(nums.size() - i, '0');
            }
        }
    }
};
```

### **Go**

```go
func findDifferentBinaryString(nums []string) string {
	mask := 0
	for _, x := range nums {
		mask |= 1 << strings.Count(x, "1")
	}
	for i := 0; ; i++ {
		if mask>>i&1 == 0 {
			return strings.Repeat("1", i) + strings.Repeat("0", len(nums)-i)
		}
	}
}
```

### **...**

```

```

<!-- tabs:end -->
