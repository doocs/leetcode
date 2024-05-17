---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2950.Number%20of%20Divisible%20Substrings/README_EN.md
tags:
    - Hash Table
    - String
    - Counting
    - Prefix Sum
---

<!-- problem:start -->

# [2950. Number of Divisible Substrings 🔒](https://leetcode.com/problems/number-of-divisible-substrings)

[中文文档](/solution/2900-2999/2950.Number%20of%20Divisible%20Substrings/README.md)

## Description

<!-- description:start -->

<p>Each character of the English alphabet has been mapped to a digit as shown below.</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2950.Number%20of%20Divisible%20Substrings/images/old_phone_digits.png" style="padding: 10px; width: 200px; height: 200px;" /></p>

<p>A string is <strong>divisible</strong> if the sum of the mapped values of its characters is divisible by its length.</p>

<p>Given a string <code>s</code>, return <em>the number of <strong>divisible substrings</strong> of</em> <code>s</code>.</p>

<p>A <strong>substring</strong> is a contiguous non-empty sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<table border="1" cellspacing="3" style="border-collapse: separate; text-align: center;">
	<tbody>
		<tr>
			<th style="padding: 5px; border: 1px solid black;">Substring</th>
			<th style="padding: 5px; border: 1px solid black;">Mapped</th>
			<th style="padding: 5px; border: 1px solid black;">Sum</th>
			<th style="padding: 5px; border: 1px solid black;">Length</th>
			<th style="padding: 5px; border: 1px solid black;">Divisible?</th>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">a</td>
			<td style="padding: 5px; border: 1px solid black;">1</td>
			<td style="padding: 5px; border: 1px solid black;">1</td>
			<td style="padding: 5px; border: 1px solid black;">1</td>
			<td style="padding: 5px; border: 1px solid black;">Yes</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">s</td>
			<td style="padding: 5px; border: 1px solid black;">7</td>
			<td style="padding: 5px; border: 1px solid black;">7</td>
			<td style="padding: 5px; border: 1px solid black;">1</td>
			<td style="padding: 5px; border: 1px solid black;">Yes</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">d</td>
			<td style="padding: 5px; border: 1px solid black;">2</td>
			<td style="padding: 5px; border: 1px solid black;">2</td>
			<td style="padding: 5px; border: 1px solid black;">1</td>
			<td style="padding: 5px; border: 1px solid black;">Yes</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">f</td>
			<td style="padding: 5px; border: 1px solid black;">3</td>
			<td style="padding: 5px; border: 1px solid black;">3</td>
			<td style="padding: 5px; border: 1px solid black;">1</td>
			<td style="padding: 5px; border: 1px solid black;">Yes</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">as</td>
			<td style="padding: 5px; border: 1px solid black;">1, 7</td>
			<td style="padding: 5px; border: 1px solid black;">8</td>
			<td style="padding: 5px; border: 1px solid black;">2</td>
			<td style="padding: 5px; border: 1px solid black;">Yes</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">sd</td>
			<td style="padding: 5px; border: 1px solid black;">7, 2</td>
			<td style="padding: 5px; border: 1px solid black;">9</td>
			<td style="padding: 5px; border: 1px solid black;">2</td>
			<td style="padding: 5px; border: 1px solid black;">No</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">df</td>
			<td style="padding: 5px; border: 1px solid black;">2, 3</td>
			<td style="padding: 5px; border: 1px solid black;">5</td>
			<td style="padding: 5px; border: 1px solid black;">2</td>
			<td style="padding: 5px; border: 1px solid black;">No</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">asd</td>
			<td style="padding: 5px; border: 1px solid black;">1, 7, 2</td>
			<td style="padding: 5px; border: 1px solid black;">10</td>
			<td style="padding: 5px; border: 1px solid black;">3</td>
			<td style="padding: 5px; border: 1px solid black;">No</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">sdf</td>
			<td style="padding: 5px; border: 1px solid black;">7, 2, 3</td>
			<td style="padding: 5px; border: 1px solid black;">12</td>
			<td style="padding: 5px; border: 1px solid black;">3</td>
			<td style="padding: 5px; border: 1px solid black;">Yes</td>
		</tr>
		<tr>
			<td style="padding: 5px; border: 1px solid black;">asdf</td>
			<td style="padding: 5px; border: 1px solid black;">1, 7, 2, 3</td>
			<td style="padding: 5px; border: 1px solid black;">13</td>
			<td style="padding: 5px; border: 1px solid black;">4</td>
			<td style="padding: 5px; border: 1px solid black;">No</td>
		</tr>
	</tbody>
</table>

<pre>
<strong>Input:</strong> word = &quot;asdf&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> The table above contains the details about every substring of word, and we can see that 6 of them are divisible.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;bdh&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> The 4 divisible substrings are: &quot;b&quot;, &quot;d&quot;, &quot;h&quot;, &quot;bdh&quot;.
It can be shown that there are no other substrings of word that are divisible.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;abcd&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> The 6 divisible substrings are: &quot;a&quot;, &quot;b&quot;, &quot;c&quot;, &quot;d&quot;, &quot;ab&quot;, &quot;cd&quot;.
It can be shown that there are no other substrings of word that are divisible.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 2000</code></li>
	<li><code>word</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

First, we use a hash table or array $mp$ to record the number corresponding to each letter.

Then, we enumerate the starting position $i$ of the substring, and then enumerate the ending position $j$ of the substring, calculate the numerical sum $s$ of the substring $s[i..j]$. If $s$ can be divided by $j-i+1$, then a divisible substring is found, and the answer is increased by one.

After the enumeration is over, return the answer.

The time complexity is $O(n^2)$, and the space complexity is $O(C)$. Where $n$ is the length of the string $word$, and $C$ is the size of the character set, in this question $C=26$.

<!-- tabs:start -->

```python
class Solution:
    def countDivisibleSubstrings(self, word: str) -> int:
        d = ["ab", "cde", "fgh", "ijk", "lmn", "opq", "rst", "uvw", "xyz"]
        mp = {}
        for i, s in enumerate(d, 1):
            for c in s:
                mp[c] = i
        ans = 0
        n = len(word)
        for i in range(n):
            s = 0
            for j in range(i, n):
                s += mp[word[j]]
                ans += s % (j - i + 1) == 0
        return ans
```

```java
class Solution {
    public int countDivisibleSubstrings(String word) {
        String[] d = {"ab", "cde", "fgh", "ijk", "lmn", "opq", "rst", "uvw", "xyz"};
        int[] mp = new int[26];
        for (int i = 0; i < d.length; ++i) {
            for (char c : d[i].toCharArray()) {
                mp[c - 'a'] = i + 1;
            }
        }
        int ans = 0;
        int n = word.length();
        for (int i = 0; i < n; ++i) {
            int s = 0;
            for (int j = i; j < n; ++j) {
                s += mp[word.charAt(j) - 'a'];
                ans += s % (j - i + 1) == 0 ? 1 : 0;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countDivisibleSubstrings(string word) {
        string d[9] = {"ab", "cde", "fgh", "ijk", "lmn", "opq", "rst", "uvw", "xyz"};
        int mp[26]{};
        for (int i = 0; i < 9; ++i) {
            for (char& c : d[i]) {
                mp[c - 'a'] = i + 1;
            }
        }
        int ans = 0;
        int n = word.size();
        for (int i = 0; i < n; ++i) {
            int s = 0;
            for (int j = i; j < n; ++j) {
                s += mp[word[j] - 'a'];
                ans += s % (j - i + 1) == 0 ? 1 : 0;
            }
        }
        return ans;
    }
};
```

```go
func countDivisibleSubstrings(word string) (ans int) {
	d := []string{"ab", "cde", "fgh", "ijk", "lmn", "opq", "rst", "uvw", "xyz"}
	mp := [26]int{}
	for i, s := range d {
		for _, c := range s {
			mp[c-'a'] = i + 1
		}
	}
	n := len(word)
	for i := 0; i < n; i++ {
		s := 0
		for j := i; j < n; j++ {
			s += mp[word[j]-'a']
			if s%(j-i+1) == 0 {
				ans++
			}
		}
	}
	return
}
```

```ts
function countDivisibleSubstrings(word: string): number {
    const d: string[] = ['ab', 'cde', 'fgh', 'ijk', 'lmn', 'opq', 'rst', 'uvw', 'xyz'];
    const mp: number[] = Array(26).fill(0);
    for (let i = 0; i < d.length; ++i) {
        for (const c of d[i]) {
            mp[c.charCodeAt(0) - 'a'.charCodeAt(0)] = i + 1;
        }
    }
    const n = word.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        let s = 0;
        for (let j = i; j < n; ++j) {
            s += mp[word.charCodeAt(j) - 'a'.charCodeAt(0)];
            if (s % (j - i + 1) === 0) {
                ++ans;
            }
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn count_divisible_substrings(word: String) -> i32 {
        let d = vec!["ab", "cde", "fgh", "ijk", "lmn", "opq", "rst", "uvw", "xyz"];
        let mut mp = vec![0; 26];

        for (i, s) in d.iter().enumerate() {
            s.chars().for_each(|c| {
                mp[(c as usize) - ('a' as usize)] = (i + 1) as i32;
            });
        }

        let mut ans = 0;
        let n = word.len();

        for i in 0..n {
            let mut s = 0;

            for j in i..n {
                s += mp[(word.as_bytes()[j] as usize) - ('a' as usize)];
                ans += (s % ((j - i + 1) as i32) == 0) as i32;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Hash Table + Prefix Sum + Enumeration

Similar to Solution 1, we first use a hash table or array $mp$ to record the number corresponding to each letter.

If the sum of the numbers in an integer subarray can be divided by its length, then the average value of this subarray must be an integer. And because the number of each element in the subarray is in the range of $[1, 9]$, the average value of the subarray can only be one of $1, 2, \cdots, 9$.

We can enumerate the average value $i$ of the subarray. If the sum of the elements in a subarray can be divided by $i$, suppose the subarray is $a_1, a_2, \cdots, a_k$, then $a_1 + a_2 + \cdots + a_k = i \times k$, that is, $(a_1 - i) + (a_2 - i) + \cdots + (a_k - i) = 0$. If we regard $a_k - i$ as a new element $b_k$, then the original subarray becomes $b_1, b_2, \cdots, b_k$, where $b_1 + b_2 + \cdots + b_k = 0$. We only need to find out how many subarrays in the new array have an element sum of $0$, which can be implemented with "hash table" combined with "prefix sum".

The time complexity is $O(10 \times n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $word$.

<!-- tabs:start -->

```python
class Solution:
    def countDivisibleSubstrings(self, word: str) -> int:
        d = ["ab", "cde", "fgh", "ijk", "lmn", "opq", "rst", "uvw", "xyz"]
        mp = {}
        for i, s in enumerate(d, 1):
            for c in s:
                mp[c] = i
        ans = 0
        for i in range(1, 10):
            cnt = defaultdict(int)
            cnt[0] = 1
            s = 0
            for c in word:
                s += mp[c] - i
                ans += cnt[s]
                cnt[s] += 1
        return ans
```

```java
class Solution {
    public int countDivisibleSubstrings(String word) {
        String[] d = {"ab", "cde", "fgh", "ijk", "lmn", "opq", "rst", "uvw", "xyz"};
        int[] mp = new int[26];
        for (int i = 0; i < d.length; ++i) {
            for (char c : d[i].toCharArray()) {
                mp[c - 'a'] = i + 1;
            }
        }
        int ans = 0;
        char[] cs = word.toCharArray();
        for (int i = 1; i < 10; ++i) {
            Map<Integer, Integer> cnt = new HashMap<>();
            cnt.put(0, 1);
            int s = 0;
            for (char c : cs) {
                s += mp[c - 'a'] - i;
                ans += cnt.getOrDefault(s, 0);
                cnt.merge(s, 1, Integer::sum);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countDivisibleSubstrings(string word) {
        string d[9] = {"ab", "cde", "fgh", "ijk", "lmn", "opq", "rst", "uvw", "xyz"};
        int mp[26]{};
        for (int i = 0; i < 9; ++i) {
            for (char& c : d[i]) {
                mp[c - 'a'] = i + 1;
            }
        }
        int ans = 0;
        for (int i = 1; i < 10; ++i) {
            unordered_map<int, int> cnt{{0, 1}};
            int s = 0;
            for (char& c : word) {
                s += mp[c - 'a'] - i;
                ans += cnt[s]++;
            }
        }
        return ans;
    }
};
```

```go
func countDivisibleSubstrings(word string) (ans int) {
	d := []string{"ab", "cde", "fgh", "ijk", "lmn", "opq", "rst", "uvw", "xyz"}
	mp := [26]int{}
	for i, s := range d {
		for _, c := range s {
			mp[c-'a'] = i + 1
		}
	}
	for i := 0; i < 10; i++ {
		cnt := map[int]int{0: 1}
		s := 0
		for _, c := range word {
			s += mp[c-'a'] - i
			ans += cnt[s]
			cnt[s]++
		}
	}
	return
}
```

```ts
function countDivisibleSubstrings(word: string): number {
    const d = ['ab', 'cde', 'fgh', 'ijk', 'lmn', 'opq', 'rst', 'uvw', 'xyz'];
    const mp: number[] = Array(26).fill(0);

    d.forEach((s, i) => {
        for (const c of s) {
            mp[c.charCodeAt(0) - 'a'.charCodeAt(0)] = i + 1;
        }
    });

    let ans = 0;
    for (let i = 0; i < 10; i++) {
        const cnt: { [key: number]: number } = { 0: 1 };
        let s = 0;
        for (const c of word) {
            s += mp[c.charCodeAt(0) - 'a'.charCodeAt(0)] - i;
            ans += cnt[s] || 0;
            cnt[s] = (cnt[s] || 0) + 1;
        }
    }
    return ans;
}
```

```rust
use std::collections::HashMap;

impl Solution {
    pub fn count_divisible_substrings(word: String) -> i32 {
        let d = vec!["ab", "cde", "fgh", "ijk", "lmn", "opq", "rst", "uvw", "xyz"];
        let mut mp: Vec<usize> = vec![0; 26];
        for (i, s) in d.iter().enumerate() {
            for c in s.chars() {
                mp[(c as usize) - ('a' as usize)] = i + 1;
            }
        }
        let mut ans = 0;
        for i in 0..10 {
            let mut cnt: HashMap<i32, i32> = HashMap::new();
            cnt.insert(0, 1);
            let mut s = 0;
            for c in word.chars() {
                s += (mp[(c as usize) - ('a' as usize)] - i) as i32;
                ans += cnt.get(&s).cloned().unwrap_or(0);
                *cnt.entry(s).or_insert(0) += 1;
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
