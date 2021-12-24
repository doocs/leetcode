# [763. 划分字母区间](https://leetcode-cn.com/problems/partition-labels)

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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def partitionLabels(self, s: str) -> List[int]:
        last = defaultdict(int)
        n = len(s)
        for i in range(n):
            last[s[i]] = i
        ans = []
        left = right = 0
        for i in range(n):
            right = max(right, last[s[i]])
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
        int[] last = new int[128];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            last[s.charAt(i)] = i;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0, left = 0, right = 0; i < n; ++i) {
            right = Math.max(right, last[s.charAt(i)]);
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
    let last = new Array(128);
    for (let i = 0; i < n; i++) {
        last[s.charCodeAt(i)] = i;
    }
    let ans = [];
    let left = 0,
        right = 0;
    for (let i = 0; i < n; i++) {
        right = Math.max(right, last[s.charCodeAt(i)]);
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
        int n = s.size();
        vector<int> last(128);
        for (int i = 0; i < n; ++i) last[s[i]] = i;
        vector<int> ans;
        for (int i = 0, left = 0, right = 0; i < n; ++i)
        {
            right = max(right, last[s[i]]);
            if (i == right)
            {
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
	n := len(s)
	last := make([]int, 128)
	for i := 0; i < n; i++ {
		last[s[i]] = i
	}
	var ans []int
	left, right := 0, 0
	for i := 0; i < n; i++ {
		right = max(right, last[s[i]])
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

### **...**

```

```

<!-- tabs:end -->
