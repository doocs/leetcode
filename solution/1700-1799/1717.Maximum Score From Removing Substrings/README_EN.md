---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1717.Maximum%20Score%20From%20Removing%20Substrings/README_EN.md
rating: 1867
source: Biweekly Contest 43 Q2
tags:
    - Stack
    - Greedy
    - String
---

<!-- problem:start -->

# [1717. Maximum Score From Removing Substrings](https://leetcode.com/problems/maximum-score-from-removing-substrings)

[中文文档](/solution/1700-1799/1717.Maximum%20Score%20From%20Removing%20Substrings/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> and two integers <code>x</code> and <code>y</code>. You can perform two types of operations any number of times.</p>

<ul>
	<li>Remove substring <code>&quot;ab&quot;</code> and gain <code>x</code> points.

    <ul>
    	<li>For example, when removing <code>&quot;ab&quot;</code> from <code>&quot;c<u>ab</u>xbae&quot;</code> it becomes <code>&quot;cxbae&quot;</code>.</li>
    </ul>
    </li>
    <li>Remove substring <code>&quot;ba&quot;</code> and gain <code>y</code> points.
    <ul>
    	<li>For example, when removing <code>&quot;ba&quot;</code> from <code>&quot;cabx<u>ba</u>e&quot;</code> it becomes <code>&quot;cabxe&quot;</code>.</li>
    </ul>
    </li>

</ul>

<p>Return <em>the maximum points you can gain after applying the above operations on</em> <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cdbcbbaaabab&quot;, x = 4, y = 5
<strong>Output:</strong> 19
<strong>Explanation:</strong>
- Remove the &quot;ba&quot; underlined in &quot;cdbcbbaaa<u>ba</u>b&quot;. Now, s = &quot;cdbcbbaaab&quot; and 5 points are added to the score.
- Remove the &quot;ab&quot; underlined in &quot;cdbcbbaa<u>ab</u>&quot;. Now, s = &quot;cdbcbbaa&quot; and 4 points are added to the score.
- Remove the &quot;ba&quot; underlined in &quot;cdbcb<u>ba</u>a&quot;. Now, s = &quot;cdbcba&quot; and 5 points are added to the score.
- Remove the &quot;ba&quot; underlined in &quot;cdbc<u>ba</u>&quot;. Now, s = &quot;cdbc&quot; and 5 points are added to the score.
Total score = 5 + 4 + 5 + 5 = 19.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabbaaxybbaabb&quot;, x = 5, y = 4
<strong>Output:</strong> 20
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= x, y &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

We can assume that the score of substring "ab" is always no less than the score of substring "ba". If not, we can swap "a" and "b", and simultaneously swap $x$ and $y$.

Next, we only need to consider the case where the string contains only "a" and "b". If the string contains other characters, we can treat them as split points, dividing the string into several substrings that contain only "a" and "b", and then calculate the score for each substring separately.

We observe that for a substring containing only "a" and "b", no matter what operations we take, we will eventually be left with only one type of character, or an empty string. Since each operation removes one "a" and one "b" simultaneously, the total number of operations is fixed. We can greedily remove "ab" first, then remove "ba", which ensures the maximum score.

Therefore, we can use two variables $\textit{cnt1}$ and $\textit{cnt2}$ to record the counts of "a" and "b" respectively, then traverse the string and update $\textit{cnt1}$ and $\textit{cnt2}$ according to different cases of the current character, while calculating the score.

For the current character $c$ being traversed:

- If $c$ is "a", since we want to remove "ab" first, we don't eliminate this character at this time, only increment $\textit{cnt1}$;
- If $c$ is "b", if $\textit{cnt1} > 0$ at this time, we can eliminate one "ab" and add $x$ points; otherwise, we can only increment $\textit{cnt2}$;
- If $c$ is another character, then for this substring, we have $\textit{cnt2}$ "b"s and $\textit{cnt1}$ "a"s left. We can eliminate $\min(\textit{cnt1}, \textit{cnt2})$ "ba"s and add several $y$ points.

After traversal, we need to handle the remaining "ba"s and add several $y$ points.

The time complexity is $O(n)$, where $n$ is the length of string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumGain(self, s: str, x: int, y: int) -> int:
        a, b = "a", "b"
        if x < y:
            x, y = y, x
            a, b = b, a
        ans = cnt1 = cnt2 = 0
        for c in s:
            if c == a:
                cnt1 += 1
            elif c == b:
                if cnt1:
                    ans += x
                    cnt1 -= 1
                else:
                    cnt2 += 1
            else:
                ans += min(cnt1, cnt2) * y
                cnt1 = cnt2 = 0
        ans += min(cnt1, cnt2) * y
        return ans
```

#### Java

```java
class Solution {
    public int maximumGain(String s, int x, int y) {
        char a = 'a', b = 'b';
        if (x < y) {
            int t = x;
            x = y;
            y = t;
            char c = a;
            a = b;
            b = c;
        }
        int ans = 0, cnt1 = 0, cnt2 = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == a) {
                cnt1++;
            } else if (c == b) {
                if (cnt1 > 0) {
                    ans += x;
                    cnt1--;
                } else {
                    cnt2++;
                }
            } else {
                ans += Math.min(cnt1, cnt2) * y;
                cnt1 = 0;
                cnt2 = 0;
            }
        }
        ans += Math.min(cnt1, cnt2) * y;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumGain(string s, int x, int y) {
        char a = 'a', b = 'b';
        if (x < y) {
            swap(x, y);
            swap(a, b);
        }

        int ans = 0, cnt1 = 0, cnt2 = 0;
        for (char c : s) {
            if (c == a) {
                cnt1++;
            } else if (c == b) {
                if (cnt1) {
                    ans += x;
                    cnt1--;
                } else {
                    cnt2++;
                }
            } else {
                ans += min(cnt1, cnt2) * y;
                cnt1 = 0;
                cnt2 = 0;
            }
        }
        ans += min(cnt1, cnt2) * y;
        return ans;
    }
};
```

#### Go

```go
func maximumGain(s string, x int, y int) (ans int) {
	a, b := 'a', 'b'
	if x < y {
		x, y = y, x
		a, b = b, a
	}

	var cnt1, cnt2 int
	for _, c := range s {
		if c == a {
			cnt1++
		} else if c == b {
			if cnt1 > 0 {
				ans += x
				cnt1--
			} else {
				cnt2++
			}
		} else {
			ans += min(cnt1, cnt2) * y
			cnt1, cnt2 = 0, 0
		}
	}
	ans += min(cnt1, cnt2) * y
	return
}
```

#### TypeScript

```ts
function maximumGain(s: string, x: number, y: number): number {
    let [a, b] = ['a', 'b'];
    if (x < y) {
        [x, y] = [y, x];
        [a, b] = [b, a];
    }

    let [ans, cnt1, cnt2] = [0, 0, 0];
    for (let c of s) {
        if (c === a) {
            cnt1++;
        } else if (c === b) {
            if (cnt1) {
                ans += x;
                cnt1--;
            } else {
                cnt2++;
            }
        } else {
            ans += Math.min(cnt1, cnt2) * y;
            cnt1 = 0;
            cnt2 = 0;
        }
    }
    ans += Math.min(cnt1, cnt2) * y;
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_gain(s: String, mut x: i32, mut y: i32) -> i32 {
        let (mut a, mut b) = ('a', 'b');
        if x < y {
            std::mem::swap(&mut x, &mut y);
            std::mem::swap(&mut a, &mut b);
        }

        let mut ans = 0;
        let mut cnt1 = 0;
        let mut cnt2 = 0;

        for c in s.chars() {
            if c == a {
                cnt1 += 1;
            } else if c == b {
                if cnt1 > 0 {
                    ans += x;
                    cnt1 -= 1;
                } else {
                    cnt2 += 1;
                }
            } else {
                ans += cnt1.min(cnt2) * y;
                cnt1 = 0;
                cnt2 = 0;
            }
        }

        ans += cnt1.min(cnt2) * y;
        ans
    }
}
```

#### JavaScript

```js
function maximumGain(s, x, y) {
    let [a, b] = ['a', 'b'];
    if (x < y) {
        [x, y] = [y, x];
        [a, b] = [b, a];
    }

    let [ans, cnt1, cnt2] = [0, 0, 0];
    for (let c of s) {
        if (c === a) {
            cnt1++;
        } else if (c === b) {
            if (cnt1) {
                ans += x;
                cnt1--;
            } else {
                cnt2++;
            }
        } else {
            ans += Math.min(cnt1, cnt2) * y;
            cnt1 = 0;
            cnt2 = 0;
        }
    }
    ans += Math.min(cnt1, cnt2) * y;
    return ans;
}
```

#### C#

```cs
public class Solution {
    public int MaximumGain(string s, int x, int y) {
        char a = 'a', b = 'b';
        if (x < y) {
            (x, y) = (y, x);
            (a, b) = (b, a);
        }

        int ans = 0, cnt1 = 0, cnt2 = 0;
        foreach (char c in s) {
            if (c == a) {
                cnt1++;
            } else if (c == b) {
                if (cnt1 > 0) {
                    ans += x;
                    cnt1--;
                } else {
                    cnt2++;
                }
            } else {
                ans += Math.Min(cnt1, cnt2) * y;
                cnt1 = 0;
                cnt2 = 0;
            }
        }

        ans += Math.Min(cnt1, cnt2) * y;
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
