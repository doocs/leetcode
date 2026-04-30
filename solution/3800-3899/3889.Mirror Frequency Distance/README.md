---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3889.Mirror%20Frequency%20Distance/README.md
rating: 1312
source: 第 496 场周赛 Q1
tags:
    - 哈希表
    - 字符串
    - 计数
---

<!-- problem:start -->

# [3889. 镜像频次距离](https://leetcode.cn/problems/mirror-frequency-distance)

[English Version](/solution/3800-3899/3889.Mirror%20Frequency%20Distance/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由小写英文字母和数字组成的字符串 <code>s</code>。</p>

<p>对于每个字符，其&nbsp;<strong>镜像字符</strong>&nbsp;根据逆序定义其字符集合：</p>

<ul>
	<li>对于字母，某字符的镜像字符是字母表中从末尾与其位置相同的字母。
	<ul>
		<li>例如，<code>'a'</code> 的镜像字符是 <code>'z'</code>，<code>'b'</code> 的镜像字符是 <code>'y'</code>，以此类推。</li>
	</ul>
	</li>
	<li>对于数字，某字符的镜像字符是范围 <code>'0'</code> 到 <code>'9'</code> 中从末尾与其位置相同的数字。
	<ul>
		<li>例如，<code>'0'</code> 的镜像字符是 <code>'9'</code>，<code>'1'</code> 的镜像字符是 <code>'8'</code>，以此类推。</li>
	</ul>
	</li>
</ul>

<p>对于字符串中每个<strong>&nbsp;唯一</strong>&nbsp;字符 <code>c</code>：</p>

<ul>
	<li>设 <code>m</code> 为其<strong>&nbsp;镜像字符</strong>&nbsp;。</li>
	<li>设 <code>freq(x)</code> 表示字符 <code>x</code> 在字符串中出现的次数。</li>
	<li>计算其与镜像字符出现次数之间的&nbsp;<strong>绝对差</strong>，定义为：<code>|freq(c) - freq(m)|</code></li>
</ul>

<p>镜像对 <code>(c, m)</code> 和 <code>(m, c)</code> 被视为相同，只能被计算&nbsp;<strong>一次&nbsp;</strong>。</p>

<p>返回一个整数，表示所有这些<strong>&nbsp;不同的镜像对</strong>&nbsp;的绝对差之和。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "ab1z9"</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>对于每个镜像对：</p>

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

<p>因此，答案是 <code>0 + 1 + 1 + 1 = 3</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "4m7n"</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

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

<p>因此，答案是 <code>1 + 0 + 1 = 2</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><strong>输入：</strong>s = "byby"</span></p>

<p><span class="example-io"><strong>输出：</strong>0</span></p>

<p><strong>解释：</strong></p>

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

<p>因此，答案是 0 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;仅由小写英文字母和数字组成。</li>
</ul>

<!-- description:end -->

## 解法

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
