---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1239.Maximum%20Length%20of%20a%20Concatenated%20String%20with%20Unique%20Characters/README_EN.md
rating: 1719
source: Weekly Contest 160 Q3
tags:
    - Bit Manipulation
    - Array
    - String
    - Backtracking
---

<!-- problem:start -->

# [1239. Maximum Length of a Concatenated String with Unique Characters](https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters)

[中文文档](/solution/1200-1299/1239.Maximum%20Length%20of%20a%20Concatenated%20String%20with%20Unique%20Characters/README.md)

## Description

<!-- description:start -->

<p>You are given an array of strings <code>arr</code>. A string <code>s</code> is formed by the <strong>concatenation</strong> of a <strong>subsequence</strong> of <code>arr</code> that has <strong>unique characters</strong>.</p>

<p>Return <em>the <strong>maximum</strong> possible length</em> of <code>s</code>.</p>

<p>A <strong>subsequence</strong> is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [&quot;un&quot;,&quot;iq&quot;,&quot;ue&quot;]
<strong>Output:</strong> 4
<strong>Explanation:</strong> All the valid concatenations are:
- &quot;&quot;
- &quot;un&quot;
- &quot;iq&quot;
- &quot;ue&quot;
- &quot;uniq&quot; (&quot;un&quot; + &quot;iq&quot;)
- &quot;ique&quot; (&quot;iq&quot; + &quot;ue&quot;)
Maximum length is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [&quot;cha&quot;,&quot;r&quot;,&quot;act&quot;,&quot;ers&quot;]
<strong>Output:</strong> 6
<strong>Explanation:</strong> Possible longest valid concatenations are &quot;chaers&quot; (&quot;cha&quot; + &quot;ers&quot;) and &quot;acters&quot; (&quot;act&quot; + &quot;ers&quot;).
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [&quot;abcdefghijklmnopqrstuvwxyz&quot;]
<strong>Output:</strong> 26
<strong>Explanation:</strong> The only string in arr has all 26 characters.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 16</code></li>
	<li><code>1 &lt;= arr[i].length &lt;= 26</code></li>
	<li><code>arr[i]</code> contains only lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: State Compression + Bit Manipulation

Since the problem requires that the characters in the subsequence must not be repeated and all characters are lowercase letters, we can use a binary integer of length $26$ to represent a subsequence. The $i$-th bit being $1$ indicates that the subsequence contains the $i$-th character, and $0$ indicates that it does not contain the $i$-th character.

We can use an array $s$ to store the states of all subsequences that meet the conditions. Initially, $s$ contains only one element $0$.

Then we traverse the array $\textit{arr}$. For each string $t$, we use an integer $x$ to represent the state of $t$. Then we traverse the array $s$. For each state $y$, if $x$ and $y$ have no common characters, we add the union of $x$ and $y$ to $s$ and update the answer.

Finally, we return the answer.

The time complexity is $O(2^n + L)$, and the space complexity is $O(2^n)$. Here, $n$ is the length of the string array, and $L$ is the sum of the lengths of all strings in the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxLength(self, arr: List[str]) -> int:
        s = [0]
        for t in arr:
            x = 0
            for b in map(lambda c: ord(c) - 97, t):
                if x >> b & 1:
                    x = 0
                    break
                x |= 1 << b
            if x:
                s.extend((x | y) for y in s if (x & y) == 0)
        return max(x.bit_count() for x in s)
```

#### Java

```java
class Solution {
    public int maxLength(List<String> arr) {
        List<Integer> s = new ArrayList<>();
        s.add(0);
        int ans = 0;
        for (var t : arr) {
            int x = 0;
            for (char c : t.toCharArray()) {
                int b = c - 'a';
                if ((x >> b & 1) == 1) {
                    x = 0;
                    break;
                }
                x |= 1 << b;
            }
            if (x > 0) {
                for (int i = s.size() - 1; i >= 0; --i) {
                    int y = s.get(i);
                    if ((x & y) == 0) {
                        s.add(x | y);
                        ans = Math.max(ans, Integer.bitCount(x | y));
                    }
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxLength(vector<string>& arr) {
        vector<int> s = {0};
        int ans = 0;
        for (const string& t : arr) {
            int x = 0;
            for (char c : t) {
                int b = c - 'a';
                if ((x >> b & 1) == 1) {
                    x = 0;
                    break;
                }
                x |= 1 << b;
            }
            if (x > 0) {
                for (int i = s.size() - 1; i >= 0; --i) {
                    int y = s[i];
                    if ((x & y) == 0) {
                        s.push_back(x | y);
                        ans = max(ans, __builtin_popcount(x | y));
                    }
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxLength(arr []string) (ans int) {
	s := []int{0}
	for _, t := range arr {
		x := 0
		for _, c := range t {
			b := int(c - 'a')
			if (x>>b)&1 == 1 {
				x = 0
				break
			}
			x |= 1 << b
		}
		if x > 0 {
			for i := len(s) - 1; i >= 0; i-- {
				y := s[i]
				if (x & y) == 0 {
					s = append(s, x|y)
					ans = max(ans, bits.OnesCount(uint(x|y)))
				}
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxLength(arr: string[]): number {
    const s: number[] = [0];
    let ans = 0;
    for (const t of arr) {
        let x = 0;
        for (const c of t) {
            const b = c.charCodeAt(0) - 97;
            if ((x >> b) & 1) {
                x = 0;
                break;
            }
            x |= 1 << b;
        }

        if (x > 0) {
            for (let i = s.length - 1; ~i; --i) {
                const y = s[i];
                if ((x & y) === 0) {
                    s.push(x | y);
                    ans = Math.max(ans, bitCount(x | y));
                }
            }
        }
    }

    return ans;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
