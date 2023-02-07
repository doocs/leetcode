# [1239. 串联字符串的最大长度](https://leetcode.cn/problems/maximum-length-of-a-concatenated-string-with-unique-characters)

[English Version](/solution/1200-1299/1239.Maximum%20Length%20of%20a%20Concatenated%20String%20with%20Unique%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串数组 <code>arr</code>，字符串 <code>s</code> 是将 <code>arr</code>&nbsp;的含有 <strong>不同字母</strong> 的&nbsp;<strong>子序列</strong> 字符串 <strong>连接</strong> 所得的字符串。</p>

<p>请返回所有可行解 <code>s</code> 中最长长度。</p>

<p><strong>子序列</strong> 是一种可以从另一个数组派生而来的数组，通过删除某些元素或不删除元素而不改变其余元素的顺序。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = ["un","iq","ue"]
<strong>输出：</strong>4
<strong>解释：</strong>所有可能的串联组合是：
- ""
- "un"
- "iq"
- "ue"
- "uniq" ("un" + "iq")
- "ique" ("iq" + "ue")
最大长度为 4。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = ["cha","r","act","ers"]
<strong>输出：</strong>6
<strong>解释：</strong>可能的解答有 "chaers" 和 "acters"。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = ["abcdefghijklmnopqrstuvwxyz"]
<strong>输出：</strong>26
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 16</code></li>
	<li><code>1 &lt;= arr[i].length &lt;= 26</code></li>
	<li><code>arr[i]</code>&nbsp;中只含有小写英文字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：位运算 + 状态压缩**

状态压缩，用一个 32 位数记录字母的出现情况，`masks` 存储之前枚举的字符串。

时间复杂度 $O(2^n + L)$，空间复杂度 $O(2^n)$。其中 $n$ 和 $L$ 分别是字符串数组的长度和字符串数组中字符串的长度之和。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxLength(self, arr: List[str]) -> int:
        ans = 0
        masks = [0]
        for s in arr:
            mask = 0
            for c in s:
                i = ord(c) - ord('a')
                if mask >> i & 1:
                    mask = 0
                    break
                mask |= 1 << i
            if mask == 0:
                continue
            for m in masks:
                if m & mask == 0:
                    masks.append(m | mask)
                    ans = max(ans, (m | mask).bit_count())
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxLength(List<String> arr) {
        int ans = 0;
        List<Integer> masks = new ArrayList<>();
        masks.add(0);
        for (var s : arr) {
            int mask = 0;
            for (int i = 0; i < s.length(); ++i) {
                int j = s.charAt(i) - 'a';
                if (((mask >> j) & 1) == 1) {
                    mask = 0;
                    break;
                }
                mask |= 1 << j;
            }
            if (mask == 0) {
                continue;
            }
            int n = masks.size();
            for (int i = 0; i < n; ++i) {
                int m = masks.get(i);
                if ((m & mask) == 0) {
                    masks.add(m | mask);
                    ans = Math.max(ans, Integer.bitCount(m | mask));
                }
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
    int maxLength(vector<string>& arr) {
        int ans = 0;
        vector<int> masks = {0};
        for (auto& s : arr) {
            int mask = 0;
            for (auto& c : s) {
                int i = c - 'a';
                if (mask >> i & 1) {
                    mask = 0;
                    break;
                }
                mask |= 1 << i;
            }
            if (mask == 0) {
                continue;
            }
            int n = masks.size();
            for (int i = 0; i < n; ++i) {
                int m = masks[i];
                if ((m & mask) == 0) {
                    masks.push_back(m | mask);
                    ans = max(ans, __builtin_popcount(m | mask));
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxLength(arr []string) (ans int) {
	masks := []int{0}
	for _, s := range arr {
		mask := 0
		for _, c := range s {
			i := int(c - 'a')
			if mask>>i&1 == 1 {
				mask = 0
				break
			}
			mask |= 1 << i
		}
		if mask == 0 {
			continue
		}
		n := len(masks)
		for _, m := range masks[:n] {
			if m&mask == 0 {
				masks = append(masks, m|mask)
				ans = max(ans, bits.OnesCount(uint(m|mask)))
			}
		}
	}
	return
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
