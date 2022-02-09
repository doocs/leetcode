# [763. Partition Labels](https://leetcode.com/problems/partition-labels)

[中文文档](/solution/0700-0799/0763.Partition%20Labels/README.md)

## Description

<p>A string <code>S</code> of lowercase English letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.</p>

<p>&nbsp;</p>

<p><b>Example 1:</b></p>

<pre>
<b>Input:</b> S = &quot;ababcbacadefegdehijhklij&quot;
<b>Output:</b> [9,7,8]
<b>Explanation:</b>
The partition is &quot;ababcbaca&quot;, &quot;defegde&quot;, &quot;hijhklij&quot;.
This is a partition so that each letter appears in at most one part.
A partition like &quot;ababcbacadefegde&quot;, &quot;hijhklij&quot; is incorrect, because it splits S into less parts.
</pre>

<p>&nbsp;</p>

<p><b>Note:</b></p>

<ul>
	<li><code>S</code> will have length in range <code>[1, 500]</code>.</li>
	<li><code>S</code> will consist of lowercase English&nbsp;letters (<code>&#39;a&#39;</code> to <code>&#39;z&#39;</code>) only.</li>
</ul>

<p>&nbsp;</p>

## Solutions

<!-- tabs:start -->

### **Python3**

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
        for (int i = 0, left = 0, right = 0; i < n; ++i)
        {
            right = max(right, last[s[i] - 'a']);
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

### **...**

```

```

<!-- tabs:end -->
