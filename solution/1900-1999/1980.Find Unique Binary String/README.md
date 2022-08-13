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

"1" 在长度为 n 的二进制字符串中出现的次数可为 0, 1, 2, ..., n （共有 n + 1 种可能）。

由于 nums 的长度为 n (n 种可能)，因此我们一定可以找出一个新的二进制字符串，满足 "1" 在字符串中出现次数与 nums 中每个字符串不同。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findDifferentBinaryString(self, nums: List[str]) -> str:
        s = set(num.count("1") for num in nums)
        n = len(nums)
        for i in range(n + 1):
            if i not in s:
                return "1" * i + "0" * (n - i)
        return ""
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String findDifferentBinaryString(String[] nums) {
        Set<Integer> s = count(nums);
        int n = nums.length;
        for (int i = 0; i < n + 1; ++i) {
            if (!s.contains(i)) {
                return "1".repeat(i) + "0".repeat(n - i);
            }
        }
        return "";
    }

    private Set<Integer> count(String[] nums) {
        Set<Integer> s = new HashSet<>();
        for (String num : nums) {
            int t = 0;
            for (char c : num.toCharArray()) {
                if (c == '1') {
                    ++t;
                }
            }
            s.add(t);
        }
        return s;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string findDifferentBinaryString(vector<string>& nums) {
        auto s = count(nums);
        for (int i = 0, n = nums.size(); i < n + 1; ++i) {
            if (!s.count(i))
                return repeat("1", i) + repeat("0", n - i);
        }
        return "";
    }

    unordered_set<int> count(vector<string>& nums) {
        unordered_set<int> s;
        for (auto& num : nums) {
            int t = 0;
            for (char c : num) {
                if (c == '1')
                    ++t;
            }
            s.insert(t);
        }
        return s;
    }

    string repeat(string s, int n) {
        string res = "";
        for (int i = 0; i < n; ++i) {
            res += s;
        }
        return res;
    }
};
```

### **Go**

```go
func findDifferentBinaryString(nums []string) string {
	count := func() []bool {
		s := make([]bool, 17)
		for _, num := range nums {
			t := 0
			for _, c := range num {
				if c == '1' {
					t++
				}
			}
			s[t] = true
		}
		return s
	}
	s := count()
	for i, n := 0, len(nums); i <= n; i++ {
		if !s[i] {
			return strings.Repeat("1", i) + strings.Repeat("0", n-i)
		}
	}
	return ""
}
```

### **...**

```

```

<!-- tabs:end -->
