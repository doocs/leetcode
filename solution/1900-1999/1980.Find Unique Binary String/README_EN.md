---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1980.Find%20Unique%20Binary%20String/README_EN.md
rating: 1361
source: Weekly Contest 255 Q2
tags:
    - Array
    - Hash Table
    - String
    - Backtracking
---

<!-- problem:start -->

# [1980. Find Unique Binary String](https://leetcode.com/problems/find-unique-binary-string)

[中文文档](/solution/1900-1999/1980.Find%20Unique%20Binary%20String/README.md)

## Description

<!-- description:start -->

<p>Given an array of strings <code>nums</code> containing <code>n</code> <strong>unique</strong> binary strings each of length <code>n</code>, return <em>a binary string of length </em><code>n</code><em> that <strong>does not appear</strong> in </em><code>nums</code><em>. If there are multiple answers, you may return <strong>any</strong> of them</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [&quot;01&quot;,&quot;10&quot;]
<strong>Output:</strong> &quot;11&quot;
<strong>Explanation:</strong> &quot;11&quot; does not appear in nums. &quot;00&quot; would also be correct.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [&quot;00&quot;,&quot;01&quot;]
<strong>Output:</strong> &quot;11&quot;
<strong>Explanation:</strong> &quot;11&quot; does not appear in nums. &quot;10&quot; would also be correct.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [&quot;111&quot;,&quot;011&quot;,&quot;001&quot;]
<strong>Output:</strong> &quot;101&quot;
<strong>Explanation:</strong> &quot;101&quot; does not appear in nums. &quot;000&quot;, &quot;010&quot;, &quot;100&quot;, and &quot;110&quot; would also be correct.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 16</code></li>
	<li><code>nums[i].length == n</code></li>
	<li><code>nums[i] </code>is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
	<li>All the strings of <code>nums</code> are <strong>unique</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting + Enumeration

Since the number of `'1'`s in a binary string of length $n$ can be $0, 1, 2, \cdots, n$ (a total of $n + 1$ possibilities), we can always find a new binary string whose count of `'1'`s differs from every string in $\textit{nums}$.

We use an integer $\textit{mask}$ to record the counts of `'1'`s across all strings, where the $i$-th bit of $\textit{mask}$ being $1$ indicates that a binary string of length $n$ with exactly $i$ occurrences of `'1'` exists in $\textit{nums}$, and $0$ otherwise.

We then enumerate $i$ starting from $0$, representing the count of `'1'`s in a binary string of length $n$. If the $i$-th bit of $\textit{mask}$ is $0$, it means no binary string of length $n$ with exactly $i$ occurrences of `'1'` exists, and we can return that string as the answer.

The time complexity is $O(L)$, where $L$ is the total length of all strings in $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findDifferentBinaryString(self, nums: List[str]) -> str:
        mask = 0
        for x in nums:
            mask |= 1 << x.count("1")
        for i in count(0):
            if mask >> i & 1 ^ 1:
                return "1" * i + "0" * (len(nums) - i)
```

#### Java

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

#### C++

```cpp
class Solution {
public:
    string findDifferentBinaryString(vector<string>& nums) {
        int mask = 0;
        for (auto& x : nums) {
            int cnt = count(x.begin(), x.end(), '1');
            mask |= 1 << cnt;
        }
        for (int i = 0;; ++i) {
            if (mask >> i & 1 ^ 1) {
                return string(i, '1') + string(nums.size() - i, '0');
            }
        }
    }
};
```

#### Go

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

#### TypeScript

```ts
function findDifferentBinaryString(nums: string[]): string {
    let mask = 0;
    for (let x of nums) {
        const cnt = x.split('').filter(c => c === '1').length;
        mask |= 1 << cnt;
    }
    for (let i = 0; ; ++i) {
        if (((mask >> i) & 1) === 0) {
            return '1'.repeat(i) + '0'.repeat(nums.length - i);
        }
    }
}
```

#### JavaScript

```js
/**
 * @param {string[]} nums
 * @return {string}
 */
var findDifferentBinaryString = function (nums) {
    let mask = 0;
    for (let x of nums) {
        const cnt = x.split('').filter(c => c === '1').length;
        mask |= 1 << cnt;
    }
    for (let i = 0; ; ++i) {
        if (((mask >> i) & 1) === 0) {
            return '1'.repeat(i) + '0'.repeat(nums.length - i);
        }
    }
};
```

#### C#

```cs
public class Solution {
    public string FindDifferentBinaryString(string[] nums) {
        int mask = 0;
        foreach (var x in nums) {
            int cnt = x.Count(c => c == '1');
            mask |= 1 << cnt;
        }
        int i = 0;
        while ((mask >> i & 1) == 1) {
            i++;
        }
        return string.Format("{0}{1}", new string('1', i), new string('0', nums.Length - i));
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Construction

We can construct a binary string $\textit{ans}$ of length $n$, where the $i$-th bit of $\textit{ans}$ differs from the $i$-th bit of $\textit{nums}[i]$. Since all strings in $\textit{nums}$ are distinct, $\textit{ans}$ will not appear in $\textit{nums}$.

The time complexity is $O(n)$, where $n$ is the length of the strings in $\textit{nums}$. Ignoring the space used by the answer string, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findDifferentBinaryString(self, nums: List[str]) -> str:
        ans = [None] * len(nums)
        for i, s in enumerate(nums):
            ans[i] = "1" if s[i] == "0" else "0"
        return "".join(ans)
```

#### Java

```java
class Solution {
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        char[] ans = new char[n];
        for (int i = 0; i < n; i++) {
            ans[i] = nums[i].charAt(i) == '0' ? '1' : '0';
        }
        return new String(ans);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string findDifferentBinaryString(vector<string>& nums) {
        int n = nums.size();
        string ans(n, '0');
        for (int i = 0; i < n; i++) {
            ans[i] = nums[i][i] == '0' ? '1' : '0';
        }
        return ans;
    }
};
```

#### Go

```go
func findDifferentBinaryString(nums []string) string {
	ans := make([]byte, len(nums))
	for i, s := range nums {
		if s[i] == '0' {
			ans[i] = '1'
		} else {
			ans[i] = '0'
		}
	}
	return string(ans)
}
```

#### TypeScript

```ts
function findDifferentBinaryString(nums: string[]): string {
    const n = nums.length;
    const ans: string[] = new Array(n);
    for (let i = 0; i < n; i++) {
        ans[i] = nums[i][i] === '0' ? '1' : '0';
    }
    return ans.join('');
}
```

#### JavaScript

```js
/**
 * @param {string[]} nums
 * @return {string}
 */
var findDifferentBinaryString = function (nums) {
    const n = nums.length;
    const ans = new Array(n);
    for (let i = 0; i < n; i++) {
        ans[i] = nums[i][i] === '0' ? '1' : '0';
    }
    return ans.join('');
};
```

#### C#

```cs
public class Solution {
    public string FindDifferentBinaryString(string[] nums) {
        int n = nums.Length;
        char[] ans = new char[n];
        for (int i = 0; i < n; i++) {
            ans[i] = nums[i][i] == '0' ? '1' : '0';
        }
        return new string(ans);
    }
}
```

<!-- solution:end -->

<!-- tabs:end -->

<!-- problem:end -->
