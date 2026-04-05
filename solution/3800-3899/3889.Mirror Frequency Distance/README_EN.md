---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3889.Mirror%20Frequency%20Distance/README_EN.md
---

<!-- problem:start -->

# [3889. Mirror Frequency Distance](https://leetcode.com/problems/mirror-frequency-distance)

[中文文档](/solution/3800-3899/3889.Mirror%20Frequency%20Distance/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of lowercase English letters and digits.</p>

<p>For each character, its <strong>mirror character</strong> is defined by reversing the order of its character set:</p>

<ul>
	<li>For letters, the mirror of a character is the letter at the same position from the end of the alphabet.
	<ul>
		<li>For example, the mirror of <code>&#39;a&#39;</code> is <code>&#39;z&#39;</code>, and the mirror of <code>&#39;b&#39;</code> is <code>&#39;y&#39;</code>, and so on.</li>
	</ul>
	</li>
	<li>For digits, the mirror of a character is the digit at the same position from the end of the range <code>&#39;0&#39;</code> to <code>&#39;9&#39;</code>.
	<ul>
		<li>For example, the mirror of <code>&#39;0&#39;</code> is <code>&#39;9&#39;</code>, and the mirror of <code>&#39;1&#39;</code> is <code>&#39;8&#39;</code>, and so on.</li>
	</ul>
	</li>
</ul>

<p>For each <strong>unique</strong> character <code>c</code> in the string:</p>

<ul>
	<li>Let <code>m</code> be its <strong>mirror</strong> character.</li>
	<li>Let <code>freq(x)</code> denote the number of times character <code>x</code> appears in the string.</li>
	<li>Compute the <strong>absolute difference</strong> between their <strong>frequencies</strong>, defined as: <code>|freq(c) - freq(m)|</code></li>
</ul>

<p>The mirror pairs <code>(c, m)</code> and <code>(m, c)</code> are the same and must be counted <strong>only once</strong>.</p>

<p>Return an integer denoting the total sum of these values over all such <strong>distinct mirror pairs</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;ab1z9&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>For every mirror pair:</p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>c</code></th>
			<th style="border: 1px solid black;"><code>m</code></th>
			<th style="border: 1px solid black;"><code>freq(c)</code></th>
			<th style="border: 1px solid black;"><code>freq(m)</code></th>
			<th style="border: 1px solid black;"><code>|freq(c) - freq(m)|</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">a</td>
			<td style="border: 1px solid black;">z</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">b</td>
			<td style="border: 1px solid black;">y</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">8</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">9</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
	</tbody>
</table>

<p>Thus, the answer is <code>0 + 1 + 1 + 1 = 3</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;4m7n&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>c</code></th>
			<th style="border: 1px solid black;"><code>m</code></th>
			<th style="border: 1px solid black;"><code>freq(c)</code></th>
			<th style="border: 1px solid black;"><code>freq(m)</code></th>
			<th style="border: 1px solid black;"><code>|freq(c) - freq(m)|</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">m</td>
			<td style="border: 1px solid black;">n</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
	</tbody>
</table>

<p>Thus, the answer is <code>1 + 0 + 1 = 2</code>.​​​​​​​</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;byby&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>c</code></th>
			<th style="border: 1px solid black;"><code>m</code></th>
			<th style="border: 1px solid black;"><code>freq(c)</code></th>
			<th style="border: 1px solid black;"><code>freq(m)</code></th>
			<th style="border: 1px solid black;"><code>|freq(c) - freq(m)|</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">b</td>
			<td style="border: 1px solid black;">y</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
	</tbody>
</table>

<p>Thus, the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters and digits.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We first use a hash table $\textit{freq}$ to count the frequency of each character in string $s$.

Then, we iterate over each key-value pair $(c, v)$ in $\textit{freq}$, where $c$ is the character and $v$ is the number of times character $c$ appears in string $s$. For each character $c$, we compute its mirror character $m$ and calculate $|freq(c) - freq(m)|$. To avoid counting mirror pairs twice, we use a hash set $\textit{vis}$ to track already-visited characters.

Finally, we return the sum of absolute differences over all distinct mirror pairs.

The time complexity is $O(n)$, where $n$ is the length of string $s$. The space complexity is $O(|\Sigma|)$, where $\Sigma$ is the set of distinct characters in string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def mirrorFrequency(self, s: str) -> int:
        freq = Counter(s)
        ans = 0
        vis = set()
        for c, v in freq.items():
            m = (
                chr(ord("a") + 25 - (ord(c) - ord("a")))
                if c.isalpha()
                else str(9 - int(c))
            )
            if m in vis:
                continue
            vis.add(c)
            ans += abs(v - freq[m])
        return ans
```

#### Java

```java
class Solution {
    public int mirrorFrequency(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.merge(c, 1, Integer::sum);
        }

        int ans = 0;
        Set<Character> vis = new HashSet<>();

        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            char c = entry.getKey();
            int v = entry.getValue();

            char m;
            if (Character.isLetter(c)) {
                m = (char) ('a' + 25 - (c - 'a'));
            } else {
                m = (char) ('0' + (9 - (c - '0')));
            }

            if (vis.contains(m)) {
                continue;
            }
            vis.add(c);

            int mv = freq.getOrDefault(m, 0);
            ans += Math.abs(v - mv);
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int mirrorFrequency(string s) {
        unordered_map<char, int> freq;
        for (char c : s) {
            freq[c]++;
        }

        int ans = 0;
        unordered_set<char> vis;

        for (auto& [c, v] : freq) {
            char m;
            if (isalpha(c)) {
                m = 'a' + 25 - (c - 'a');
            } else {
                m = '0' + (9 - (c - '0'));
            }

            if (vis.count(m)) {
                continue;
            }
            vis.insert(c);

            int mv = freq.count(m) ? freq[m] : 0;
            ans += abs(v - mv);
        }

        return ans;
    }
};
```

#### Go

```go
func mirrorFrequency(s string) int {
	freq := make(map[rune]int)
	for _, c := range s {
		freq[c]++
	}

	ans := 0
	vis := make(map[rune]bool)

	for c, v := range freq {
		var m rune
		if c >= 'a' && c <= 'z' {
			m = 'a' + 25 - (c - 'a')
		} else {
			m = '0' + (9 - (c - '0'))
		}

		if vis[m] {
			continue
		}
		vis[c] = true

		mv := freq[m]
		ans += abs(v - mv)
	}

	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function mirrorFrequency(s: string): number {
    const freq = new Map<string, number>();
    for (const c of s) {
        freq.set(c, (freq.get(c) || 0) + 1);
    }

    let ans = 0;
    const vis = new Set<string>();

    for (const [c, v] of freq.entries()) {
        let m: string;

        if (/[a-z]/.test(c)) {
            m = String.fromCharCode('a'.charCodeAt(0) + 25 - (c.charCodeAt(0) - 'a'.charCodeAt(0)));
        } else {
            m = String(9 - Number(c));
        }

        if (vis.has(m)) {
            continue;
        }
        vis.add(c);

        const mv = freq.get(m) || 0;
        ans += Math.abs(v - mv);
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
