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

**方法一：贪心**

我们先用数组或哈希表 $last$ 记录字符串 $s$ 中每个字母最后一次出现的位置。

接下来我们使用贪心的方法，将字符串划分为尽可能多的片段。

从左到右遍历字符串 $s$，遍历的同时维护当前片段的开始下标 $j$ 和结束下标 $i$，初始均为 $0$。

对于每个访问到的字母 $c$，获取到最后一次出现的位置 $last[c]$。由于当前片段的结束下标一定不会小于 $last[c]$，因此令 $mx = \max(mx, last[c])$。

当访问到下标 $mx$ 时，意味着当前片段访问结束，当前片段的下标范围是 $[j,.. i]$，长度为 $i - j + 1$，我们将其添加到结果数组中。然后令 $j = i + 1$, 继续寻找下一个片段。

重复上述过程，直至字符串遍历结束，即可得到所有片段的长度。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为字符串 $s$ 的长度，而 $C$ 为字符集的大小。本题中 $C = 26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def partitionLabels(self, s: str) -> List[int]:
        last = {c: i for i, c in enumerate(s)}
        mx = j = 0
        ans = []
        for i, c in enumerate(s):
            mx = max(mx, last[c])
            if mx == i:
                ans.append(i - j + 1)
                j = i + 1
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
        int mx = 0, j = 0;
        for (int i = 0; i < n; ++i) {
            mx = Math.max(mx, last[s.charAt(i) - 'a']);
            if (mx == i) {
                ans.add(i - j + 1);
                j = i + 1;
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
        for (int i = 0; i < n; ++i) {
            last[s[i] - 'a'] = i;
        }
        vector<int> ans;
        int mx = 0, j = 0;
        for (int i = 0; i < n; ++i) {
            mx = max(mx, last[s[i] - 'a']);
            if (mx == i) {
                ans.push_back(i - j + 1);
                j = i + 1;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func partitionLabels(s string) (ans []int) {
	last := [26]int{}
	for i, c := range s {
		last[c-'a'] = i
	}
	var mx, j int
	for i, c := range s {
		mx = max(mx, last[c-'a'])
		if mx == i {
			ans = append(ans, i-j+1)
			j = i + 1
		}
	}
	return
}
```

### **TypeScript**

```ts
function partitionLabels(s: string): number[] {
    const last: number[] = Array(26).fill(0);
    const idx = (c: string) => c.charCodeAt(0) - 'a'.charCodeAt(0);
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        last[idx(s[i])] = i;
    }
    const ans: number[] = [];
    for (let i = 0, j = 0, mx = 0; i < n; ++i) {
        mx = Math.max(mx, last[idx(s[i])]);
        if (mx === i) {
            ans.push(i - j + 1);
            j = i + 1;
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
        let mut last = [0; 26];
        for i in 0..n {
            last[(bytes[i] - b'a') as usize] = i;
        }
        let mut ans = vec![];
        let mut j = 0;
        let mut mx = 0;
        for i in 0..n {
            mx = mx.max(last[(bytes[i] - b'a') as usize]);
            if mx == i {
                ans.push((i - j + 1) as i32);
                j = i + 1;
            }
        }
        ans
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
    const last = new Array(26).fill(0);
    const idx = c => c.charCodeAt() - 'a'.charCodeAt();
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        last[idx(s[i])] = i;
    }
    const ans = [];
    for (let i = 0, j = 0, mx = 0; i < n; ++i) {
        mx = Math.max(mx, last[idx(s[i])]);
        if (mx === i) {
            ans.push(i - j + 1);
            j = i + 1;
        }
    }
    return ans;
};
```

### **C#**

```cs
public class Solution {
    public IList<int> PartitionLabels(string s) {
        int[] last = new int[26];
        int n = s.Length;
        for (int i = 0; i < n; i++) {
            last[s[i] - 'a'] = i;
        }
        IList<int> ans = new List<int>();
        for (int i = 0, j = 0, mx = 0; i < n; ++i) {
            mx = Math.Max(mx, last[s[i] - 'a']);
            if (mx == i) {
                ans.Add(i - j + 1);
                j = i + 1;
            }
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
