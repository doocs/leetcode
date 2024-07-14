---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2268.Minimum%20Number%20of%20Keypresses/README_EN.md
tags:
    - Greedy
    - Hash Table
    - String
    - Counting
    - Sorting
---

<!-- problem:start -->

# [2268. Minimum Number of Keypresses 🔒](https://leetcode.com/problems/minimum-number-of-keypresses)

[中文文档](/solution/2200-2299/2268.Minimum%20Number%20of%20Keypresses/README.md)

## Description

<!-- description:start -->

<p>You have a keypad with <code>9</code> buttons, numbered from <code>1</code> to <code>9</code>, each mapped to lowercase English letters. You can choose which characters each button is matched to as long as:</p>

<ul>
	<li>All 26 lowercase English letters are mapped to.</li>
	<li>Each character is mapped to by <strong>exactly</strong> <code>1</code> button.</li>
	<li>Each button maps to <strong>at most</strong> <code>3</code> characters.</li>
</ul>

<p>To type the first character matched to a button, you press the button once. To type the second character, you press the button twice, and so on.</p>

<p>Given a string <code>s</code>, return <em>the <strong>minimum</strong> number of keypresses needed to type </em><code>s</code><em> using your keypad.</em></p>

<p><strong>Note</strong> that the characters mapped to by each button, and the order they are mapped in cannot be changed.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2268.Minimum%20Number%20of%20Keypresses/images/image-20220505184346-1.png" style="width: 300px; height: 293px;" />
<pre>
<strong>Input:</strong> s = &quot;apple&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> One optimal way to setup your keypad is shown above.
Type &#39;a&#39; by pressing button 1 once.
Type &#39;p&#39; by pressing button 6 once.
Type &#39;p&#39; by pressing button 6 once.
Type &#39;l&#39; by pressing button 5 once.
Type &#39;e&#39; by pressing button 3 once.
A total of 5 button presses are needed, so return 5.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2268.Minimum%20Number%20of%20Keypresses/images/image-20220505203823-1.png" style="width: 300px; height: 288px;" />
<pre>
<strong>Input:</strong> s = &quot;abcdefghijkl&quot;
<strong>Output:</strong> 15
<strong>Explanation:</strong> One optimal way to setup your keypad is shown above.
The letters &#39;a&#39; to &#39;i&#39; can each be typed by pressing a button once.
Type &#39;j&#39; by pressing button 1 twice.
Type &#39;k&#39; by pressing button 2 twice.
Type &#39;l&#39; by pressing button 3 twice.
A total of 15 button presses are needed, so return 15.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting + Greedy

First, we count the occurrence of each character in the string $s$, and record it in an array or hash table $\textit{cnt}$.

The problem requires minimizing the number of key presses, so the $9$ most frequent characters should correspond to keys $1$ to $9$, the $10$th to $18$th most frequent characters should correspond to keys $1$ to $9$ again, and so on.

Therefore, we can sort the values in $\textit{cnt}$ in descending order, and then allocate them to the keys in the order from $1$ to $9$, adding $1$ to the number of key presses after allocating every $9$ characters.

The time complexity is $O(n + |\Sigma| \times \log |\Sigma|)$, and the space complexity is $O(|\Sigma|)$. Here, $n$ is the length of the string $s$, and $\Sigma$ is the set of characters appearing in the string $s$. In this problem, $\Sigma$ is the set of lowercase letters, so $|\Sigma| = 26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumKeypresses(self, s: str) -> int:
        cnt = Counter(s)
        ans, k = 0, 1
        for i, x in enumerate(sorted(cnt.values(), reverse=True), 1):
            ans += k * x
            if i % 9 == 0:
                k += 1
        return ans
```

#### Java

```java
class Solution {
    public int minimumKeypresses(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        Arrays.sort(cnt);
        int ans = 0, k = 1;
        for (int i = 1; i <= 26; ++i) {
            ans += k * cnt[26 - i];
            if (i % 9 == 0) {
                ++k;
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
    int minimumKeypresses(string s) {
        int cnt[26]{};
        for (char& c : s) {
            ++cnt[c - 'a'];
        }
        sort(begin(cnt), end(cnt), greater<int>());
        int ans = 0, k = 1;
        for (int i = 1; i <= 26; ++i) {
            ans += k * cnt[i - 1];
            if (i % 9 == 0) {
                ++k;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minimumKeypresses(s string) (ans int) {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	sort.Ints(cnt)
	k := 1
	for i := 1; i <= 26; i++ {
		ans += k * cnt[26-i]
		if i%9 == 0 {
			k++
		}
	}
	return
}
```

#### TypeScript

```ts
function minimumKeypresses(s: string): number {
    const cnt: number[] = Array(26).fill(0);
    const a = 'a'.charCodeAt(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - a];
    }
    cnt.sort((a, b) => b - a);
    let [ans, k] = [0, 1];
    for (let i = 1; i <= 26; ++i) {
        ans += k * cnt[i - 1];
        if (i % 9 === 0) {
            ++k;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
