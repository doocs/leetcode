# [763. 划分字母区间](https://leetcode.cn/problems/partition-labels)

[English Version](/solution/0700-0799/0763.Partition%20Labels/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>字符串 <code>S</code> 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。</p>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>S = "ababcbacadefegdehijhklij"
<strong>输出：</strong>[9,7,8]
<strong>解释：</strong>
划分结果为 "ababcbaca", "defegde", "hijhklij"。
每个字母最多出现在一个片段中。
像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>S</code>的长度在<code>[1, 500]</code>之间。</li>
	<li><code>S</code>只包含小写字母 <code>'a'</code> 到 <code>'z'</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先用数组或哈希表 last 记录每个字母最后一次出现的位置。

接下来使用贪心的方法，将字符串划分为尽可能多的片段：

-   从左到右遍历字符串，遍历的同时维护当前片段的开始下标 left 和结束下标 right，初始均为 0；
-   对于每个访问到的字母 c，获取到最后一次出现的位置 `last[c]`。由于当前片段的结束下标一定不会小于 `last[c]`，因此令 `right = max(right, last[c])`；
-   当访问到下标 right 时，当前片段访问结束，当前片段的下标范围是 `[left, right]`，长度为 `right - left + 1`，将其添加到结果数组中，然后令 left = right + 1, 继续寻找下一个片段；
-   重复上述过程，直至字符串遍历结束。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def partitionLabels(self, s: str) -> List[int]:
        last = [0] * 26
        for i, c in enumerate(s):
            last[ord(c) - ord('a')] = i
        ans = []
        left = right = 0
        for i, c in enumerate(s):
            right = max(right, last[ord(c) - ord('a')])
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

### **C++**

```cpp
class Solution {
public:
    vector<int> partitionLabels(string s) {
        vector<int> last(26);
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

### **...**

```

```

<!-- tabs:end -->
