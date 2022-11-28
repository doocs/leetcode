# [763. 划分字母区间](https://leetcode.cn/problems/partition-labels)

[English Version](/solution/0700-0799/0763.Partition%20Labels/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。</p>

<p>注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 <code>s</code> 。</p>

<p>返回一个表示每个字符串片段的长度的列表。</p>

<p>&nbsp;</p>
<strong class="example">示例 1：</strong>

<pre>
<strong>输入：</strong>s = "ababcbacadefegdehijhklij"
<strong>输出：</strong>[9,7,8]
<strong>解释：</strong>
划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
每个字母最多出现在一个片段中。
像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。 </pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "eccbbbbdec"
<strong>输出：</strong>[10]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数组或哈希表 + 贪心**

我们先用数组或哈希表 `last` 记录字符串 $s$ 中每个字母最后一次出现的位置。

接下来使用贪心的方法，将字符串划分为尽可能多的片段：

从左到右遍历字符串，遍历的同时维护当前片段的开始下标 $left$ 和结束下标 $right$，初始均为 $0$。

对于每个访问到的字母 $c$，获取到最后一次出现的位置 $last[c]$。由于当前片段的结束下标一定不会小于 $last[c]$，因此令 $right = \max(right, last[c])$。

当访问到下标 $right$ 时，意味着当前片段访问结束，当前片段的下标范围是 $[left,.. right]$，长度为 $right - left + 1$，我们将其添加到结果数组中。然后令 $left = right + 1$, 继续寻找下一个片段。

重复上述过程，直至字符串遍历结束，即可得到所有片段的长度。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为字符串 $s$ 的长度，而 $C$ 为字符集的大小。本题中 $C = 26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def partitionLabels(self, s: str) -> List[int]:
        last = {c: i for i, c in enumerate(s)}
        ans = []
        left = right = 0
        for i, c in enumerate(s):
            right = max(right, last[c])
            if i == right:
                ans.append(right - left + 1)
                left = right + 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0, left = 0, right = 0; i < n; ++i) {
            right = Math.max(right, last[s.charAt(i) - 'a']);
            if (i == right) {
                ans.add(right - left + 1);
                left = right + 1;
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
    vector<int> partitionLabels(string s) {
        int last[26] = {0};
        int n = s.size();
        for (int i = 0; i < n; ++i) last[s[i] - 'a'] = i;
        vector<int> ans;
        for (int i = 0, left = 0, right = 0; i < n; ++i) {
            right = max(right, last[s[i] - 'a']);
            if (i == right) {
                ans.push_back(right - left + 1);
                left = right + 1;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func partitionLabels(s string) []int {
	last := make([]int, 26)
	n := len(s)
	for i := 0; i < n; i++ {
		last[s[i]-'a'] = i
	}
	var ans []int
	for i, left, right := 0, 0, 0; i < n; i++ {
		right = max(right, last[s[i]-'a'])
		if i == right {
			ans = append(ans, right-left+1)
			left = right + 1
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

### **TypeScript**

```ts
function partitionLabels(s: string): number[] {
    const n = s.length;
    let last = new Array(26);
    for (let i = 0; i < n; i++) {
        last[s.charCodeAt(i) - 'a'.charCodeAt(0)] = i;
    }
    let ans = [];
    let left = 0,
        right = 0;
    for (let i = 0; i < n; i++) {
        right = Math.max(right, last[s.charCodeAt(i) - 'a'.charCodeAt(0)]);
        if (i == right) {
            ans.push(right - left + 1);
            left = right + 1;
        }
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn partition_labels(s: String) -> Vec<i32> {
        let n = s.len();
        let bytes = s.as_bytes();
        let mut inx_arr = [0; 26];
        for i in 0..n {
            inx_arr[(bytes[i] - b'a') as usize] = i;
        }
        let mut res = vec![];
        let mut left = 0;
        let mut right = 0;
        for i in 0..n {
            right = right.max(inx_arr[(bytes[i] - b'a') as usize]);
            if right == i {
                res.push((right - left + 1) as i32);
                left = i + 1;
            }
        }
        res
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {number[]}
 */
var partitionLabels = function (s) {
    const n = s.length;
    let last = new Array(26);
    for (let i = 0; i < n; i++) {
        last[s.charCodeAt(i) - 'a'.charCodeAt(0)] = i;
    }
    let ans = [];
    let left = 0,
        right = 0;
    for (let i = 0; i < n; i++) {
        right = Math.max(right, last[s.charCodeAt(i) - 'a'.charCodeAt(0)]);
        if (i == right) {
            ans.push(right - left + 1);
            left = right + 1;
        }
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
