---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2434.Using%20a%20Robot%20to%20Print%20the%20Lexicographically%20Smallest%20String/README_EN.md
rating: 1953
source: Weekly Contest 314 Q3
tags:
    - Stack
    - Greedy
    - Hash Table
    - String
---

<!-- problem:start -->

# [2434. Using a Robot to Print the Lexicographically Smallest String](https://leetcode.com/problems/using-a-robot-to-print-the-lexicographically-smallest-string)

[中文文档](/solution/2400-2499/2434.Using%20a%20Robot%20to%20Print%20the%20Lexicographically%20Smallest%20String/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> and a robot that currently holds an empty string <code>t</code>. Apply one of the following operations until <code>s</code> and <code>t</code> <strong>are both empty</strong>:</p>

<ul>
	<li>Remove the <strong>first</strong> character of a string <code>s</code> and give it to the robot. The robot will append this character to the string <code>t</code>.</li>
	<li>Remove the <strong>last</strong> character of a string <code>t</code> and give it to the robot. The robot will write this character on paper.</li>
</ul>

<p>Return <em>the lexicographically smallest string that can be written on the paper.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;zza&quot;
<strong>Output:</strong> &quot;azz&quot;
<strong>Explanation:</strong> Let p denote the written string.
Initially p=&quot;&quot;, s=&quot;zza&quot;, t=&quot;&quot;.
Perform first operation three times p=&quot;&quot;, s=&quot;&quot;, t=&quot;zza&quot;.
Perform second operation three times p=&quot;azz&quot;, s=&quot;&quot;, t=&quot;&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bac&quot;
<strong>Output:</strong> &quot;abc&quot;
<strong>Explanation:</strong> Let p denote the written string.
Perform first operation twice p=&quot;&quot;, s=&quot;c&quot;, t=&quot;ba&quot;. 
Perform second operation twice p=&quot;ab&quot;, s=&quot;c&quot;, t=&quot;&quot;. 
Perform first operation p=&quot;ab&quot;, s=&quot;&quot;, t=&quot;c&quot;. 
Perform second operation p=&quot;abc&quot;, s=&quot;&quot;, t=&quot;&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bdda&quot;
<strong>Output:</strong> &quot;addb&quot;
<strong>Explanation:</strong> Let p denote the written string.
Initially p=&quot;&quot;, s=&quot;bdda&quot;, t=&quot;&quot;.
Perform first operation four times p=&quot;&quot;, s=&quot;&quot;, t=&quot;bdda&quot;.
Perform second operation four times p=&quot;addb&quot;, s=&quot;&quot;, t=&quot;&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only English lowercase letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Stack

The problem can be transformed into: given a string sequence, use an auxiliary stack to convert it into the lexicographically smallest string sequence.

We can use an array $\textit{cnt}$ to maintain the count of each character in string $s$, use a stack $\textit{stk}$ as the auxiliary stack mentioned in the problem, and use a variable $\textit{mi}$ to keep track of the smallest character not yet traversed in the string.

Traverse the string $s$. For each character $c$, first decrement its count in the array $\textit{cnt}$ and update $\textit{mi}$. Then push $c$ onto the stack. At this point, if the top element of the stack is less than or equal to $\textit{mi}$, repeatedly pop the top element from the stack and add it to the answer.

After the traversal, return the answer.

The time complexity is $O(n + |\Sigma|)$, and the space complexity is $O(n)$, where $n$ is the length of the string $s$ and $|\Sigma|$ is the size of the character set, which is $26$ in this problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def robotWithString(self, s: str) -> str:
        cnt = Counter(s)
        ans = []
        stk = []
        mi = 'a'
        for c in s:
            cnt[c] -= 1
            while mi < 'z' and cnt[mi] == 0:
                mi = chr(ord(mi) + 1)
            stk.append(c)
            while stk and stk[-1] <= mi:
                ans.append(stk.pop())
        return ''.join(ans)
```

#### Java

```java
class Solution {
    public String robotWithString(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        StringBuilder ans = new StringBuilder();
        Deque<Character> stk = new ArrayDeque<>();
        char mi = 'a';
        for (char c : s.toCharArray()) {
            --cnt[c - 'a'];
            while (mi < 'z' && cnt[mi - 'a'] == 0) {
                ++mi;
            }
            stk.push(c);
            while (!stk.isEmpty() && stk.peek() <= mi) {
                ans.append(stk.pop());
            }
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string robotWithString(string s) {
        int cnt[26] = {0};
        for (char& c : s) ++cnt[c - 'a'];
        char mi = 'a';
        string stk;
        string ans;
        for (char& c : s) {
            --cnt[c - 'a'];
            while (mi < 'z' && cnt[mi - 'a'] == 0) ++mi;
            stk += c;
            while (!stk.empty() && stk.back() <= mi) {
                ans += stk.back();
                stk.pop_back();
            }
        }
        return ans;
    }
};
```

#### Go

```go
func robotWithString(s string) string {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	mi := byte('a')
	stk := []byte{}
	ans := []byte{}
	for i := range s {
		cnt[s[i]-'a']--
		for mi < 'z' && cnt[mi-'a'] == 0 {
			mi++
		}
		stk = append(stk, s[i])
		for len(stk) > 0 && stk[len(stk)-1] <= mi {
			ans = append(ans, stk[len(stk)-1])
			stk = stk[:len(stk)-1]
		}
	}
	return string(ans)
}
```

#### TypeScript

```ts
function robotWithString(s: string): string {
    const cnt = new Map<string, number>();
    for (const c of s) {
        cnt.set(c, (cnt.get(c) || 0) + 1);
    }
    const ans: string[] = [];
    const stk: string[] = [];
    let mi = 'a';
    for (const c of s) {
        cnt.set(c, (cnt.get(c) || 0) - 1);
        while (mi < 'z' && (cnt.get(mi) || 0) === 0) {
            mi = String.fromCharCode(mi.charCodeAt(0) + 1);
        }
        stk.push(c);
        while (stk.length > 0 && stk[stk.length - 1] <= mi) {
            ans.push(stk.pop()!);
        }
    }
    return ans.join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn robot_with_string(s: String) -> String {
        let mut cnt = [0; 26];
        for &c in s.as_bytes() {
            cnt[(c - b'a') as usize] += 1;
        }

        let mut ans = Vec::with_capacity(s.len());
        let mut stk = Vec::new();
        let mut mi = 0;

        for &c in s.as_bytes() {
            cnt[(c - b'a') as usize] -= 1;
            while mi < 26 && cnt[mi] == 0 {
                mi += 1;
            }
            stk.push(c);
            while let Some(&top) = stk.last() {
                if (top - b'a') as usize <= mi {
                    ans.push(stk.pop().unwrap());
                } else {
                    break;
                }
            }
        }

        String::from_utf8(ans).unwrap()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
