---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0246.Strobogrammatic%20Number/README_EN.md
tags:
    - Hash Table
    - Two Pointers
    - String
---

<!-- problem:start -->

# [246. Strobogrammatic Number 🔒](https://leetcode.com/problems/strobogrammatic-number)

[中文文档](/solution/0200-0299/0246.Strobogrammatic%20Number/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>num</code> which represents an integer, return <code>true</code> <em>if</em> <code>num</code> <em>is a <strong>strobogrammatic number</strong></em>.</p>

<p>A <strong>strobogrammatic number</strong> is a number that looks the same when rotated <code>180</code> degrees (looked at upside down).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;69&quot;
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;88&quot;
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;962&quot;
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 50</code></li>
	<li><code>num</code> consists of only digits.</li>
	<li><code>num</code> does not contain any leading zeros except for zero itself.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers Simulation

We define an array $d$, where $d[i]$ represents the number after rotating the digit $i$ by 180°. If $d[i]$ is $-1$, it means that the digit $i$ cannot be rotated 180° to get a valid digit.

We define two pointers $i$ and $j$, pointing to the left and right ends of the string, respectively. Then we continuously move the pointers towards the center, checking whether $d[num[i]]$ and $num[j]$ are equal. If they are not equal, it means that the string is not a strobogrammatic number, and we can directly return $false$. If $i > j$, it means that we have traversed the entire string, and we return $true$.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def isStrobogrammatic(self, num: str) -> bool:
        d = [0, 1, -1, -1, -1, -1, 9, -1, 8, 6]
        i, j = 0, len(num) - 1
        while i <= j:
            a, b = int(num[i]), int(num[j])
            if d[a] != b:
                return False
            i, j = i + 1, j - 1
        return True
```

```java
class Solution {
    public boolean isStrobogrammatic(String num) {
        int[] d = new int[] {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
        for (int i = 0, j = num.length() - 1; i <= j; ++i, --j) {
            int a = num.charAt(i) - '0', b = num.charAt(j) - '0';
            if (d[a] != b) {
                return false;
            }
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    bool isStrobogrammatic(string num) {
        vector<int> d = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
        for (int i = 0, j = num.size() - 1; i <= j; ++i, --j) {
            int a = num[i] - '0', b = num[j] - '0';
            if (d[a] != b) {
                return false;
            }
        }
        return true;
    }
};
```

```go
func isStrobogrammatic(num string) bool {
	d := []int{0, 1, -1, -1, -1, -1, 9, -1, 8, 6}
	for i, j := 0, len(num)-1; i <= j; i, j = i+1, j-1 {
		a, b := int(num[i]-'0'), int(num[j]-'0')
		if d[a] != b {
			return false
		}
	}
	return true
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
