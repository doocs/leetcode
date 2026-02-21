---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3846.Total%20Distance%20to%20Type%20a%20String%20Using%20One%20Finger/README_EN.md
---

<!-- problem:start -->

# [3846. Total Distance to Type a String Using One Finger 🔒](https://leetcode.com/problems/total-distance-to-type-a-string-using-one-finger)

[中文文档](/solution/3800-3899/3846.Total%20Distance%20to%20Type%20a%20String%20Using%20One%20Finger/README.md)

## Description

<!-- description:start -->

There is a special keyboard where keys are arranged in a rectangular grid as follows.

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<td style="border: 1px solid black;">q</td>
			<td style="border: 1px solid black;">w</td>
			<td style="border: 1px solid black;">e</td>
			<td style="border: 1px solid black;">r</td>
			<td style="border: 1px solid black;">t</td>
			<td style="border: 1px solid black;">y</td>
			<td style="border: 1px solid black;">u</td>
			<td style="border: 1px solid black;">i</td>
			<td style="border: 1px solid black;">o</td>
			<td style="border: 1px solid black;">p</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">a</td>
			<td style="border: 1px solid black;">s</td>
			<td style="border: 1px solid black;">d</td>
			<td style="border: 1px solid black;">f</td>
			<td style="border: 1px solid black;">g</td>
			<td style="border: 1px solid black;">h</td>
			<td style="border: 1px solid black;">j</td>
			<td style="border: 1px solid black;">k</td>
			<td style="border: 1px solid black;">l</td>
			<td style="border: 1px solid black;">&nbsp;</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">z</td>
			<td style="border: 1px solid black;">x</td>
			<td style="border: 1px solid black;">c</td>
			<td style="border: 1px solid black;">v</td>
			<td style="border: 1px solid black;">b</td>
			<td style="border: 1px solid black;">n</td>
			<td style="border: 1px solid black;">m</td>
			<td style="border: 1px solid black;">&nbsp;</td>
			<td style="border: 1px solid black;">&nbsp;</td>
			<td style="border: 1px solid black;">&nbsp;</td>
		</tr>
	</tbody>
</table>

<p>You are given a string <code>s</code> that consists of lowercase English letters only. Return an integer denoting the total <strong>distance</strong> to type <code>s</code> using only one finger. Your finger starts on the key <code>&#39;a&#39;</code>.</p>

<p>The <strong>distance</strong> between two keys at <code>(r1, c1)</code> and <code>(r2, c2)</code> is <code>|r1 - r2| + |c1 - c2|</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;hello&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">17</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Your finger starts at <code>&#39;a&#39;</code>, which is at <code>(1, 0)</code>.</li>
	<li>Move to <code>&#39;h&#39;</code>, which is at <code>(1, 5)</code>. The distance is <code>|1 - 1| + |0 - 5| = 5</code>.</li>
	<li>Move to <code>&#39;e&#39;</code>, which is at <code>(0, 2)</code>. The distance is <code>|1 - 0| + |5 - 2| = 4</code>.</li>
	<li>Move to <code>&#39;l&#39;</code>, which is at <code>(1, 8)</code>. The distance is <code>|0 - 1| + |2 - 8| = 7</code>.</li>
	<li>Move to <code>&#39;l&#39;</code>, which is at <code>(1, 8)</code>. The distance is <code>|1 - 1| + |8 - 8| = 0</code>.</li>
	<li>Move to <code>&#39;o&#39;</code>, which is at <code>(0, 8)</code>. The distance is <code>|1 - 0| + |8 - 8| = 1</code>.</li>
	<li>Total distance is <code>5 + 4 + 7 + 0 + 1 = 17</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;a&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Your finger starts at <code>&#39;a&#39;</code>, which is at <code>(1, 0)</code>.</li>
	<li>Move to <code>&#39;a&#39;</code>, which is at <code>(1, 0)</code>. The distance is <code>|1 - 1| + |0 - 0| = 0</code>.</li>
	<li>Total distance is 0.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists of lowercase English letters only.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We define a hash table $\textit{pos}$ to store the position of each character on the keyboard. For each character in string $s$, we calculate the distance from the previous character to the current character and accumulate it to the answer. Finally, we return the answer.

The time complexity is $O(n)$, where $n$ is the length of string $s$. The space complexity is $O(|\Sigma|)$, where $\Sigma$ is the character set, which here is 26 lowercase English letters.

<!-- tabs:start -->

#### Python3

```python
pos = {}
keys = ['qwertyuiop', 'asdfghjkl', 'zxcvbnm']
for (i, row) in enumerate(keys):
    for (j, key) in enumerate(row):
        pos[key] = (i, j)


class Solution:
    def totalDistance(self, s: str) -> int:
        pre = 'a'
        ans = 0
        for cur in s:
            x1, y1 = pos[pre]
            x2, y2 = pos[cur]
            dist = abs(x1 - x2) + abs(y1 - y2)
            ans += dist
            pre = cur
        return ans
```

#### Java

```java
class Solution {
    private static final Map<Character, int[]> pos = new HashMap<>();

    static {
        String[] keys = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        for (int i = 0; i < keys.length; i++) {
            for (int j = 0; j < keys[i].length(); j++) {
                pos.put(keys[i].charAt(j), new int[] {i, j});
            }
        }
    }

    public int totalDistance(String s) {
        char pre = 'a';
        int ans = 0;

        for (char cur : s.toCharArray()) {
            int[] p1 = pos.get(pre);
            int[] p2 = pos.get(cur);
            ans += Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
            pre = cur;
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int totalDistance(string s) {
        static unordered_map<char, pair<int, int>> pos = [] {
            unordered_map<char, pair<int, int>> m;
            vector<string> keys = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
            for (int i = 0; i < keys.size(); ++i) {
                for (int j = 0; j < keys[i].size(); ++j) {
                    m[keys[i][j]] = {i, j};
                }
            }
            return m;
        }();

        char pre = 'a';
        int ans = 0;

        for (char cur : s) {
            auto [x1, y1] = pos[pre];
            auto [x2, y2] = pos[cur];
            ans += abs(x1 - x2) + abs(y1 - y2);
            pre = cur;
        }

        return ans;
    }
};
```

#### Go

```go
var pos map[byte][2]int

func init() {
	pos = make(map[byte][2]int)
	keys := []string{"qwertyuiop", "asdfghjkl", "zxcvbnm"}
	for i, row := range keys {
		for j := 0; j < len(row); j++ {
			pos[row[j]] = [2]int{i, j}
		}
	}
}

func totalDistance(s string) int {
	pre := byte('a')
	ans := 0

	for i := 0; i < len(s); i++ {
		cur := s[i]
		p1 := pos[pre]
		p2 := pos[cur]
		ans += abs(p1[0]-p2[0]) + abs(p1[1]-p2[1])
		pre = cur
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
const pos: Record<string, [number, number]> = {};

const keys = ['qwertyuiop', 'asdfghjkl', 'zxcvbnm'];
keys.forEach((row, i) => {
    [...row].forEach((key, j) => {
        pos[key] = [i, j];
    });
});

function totalDistance(s: string): number {
    let pre = 'a';
    let ans = 0;

    for (const cur of s) {
        const [x1, y1] = pos[pre];
        const [x2, y2] = pos[cur];
        ans += Math.abs(x1 - x2) + Math.abs(y1 - y2);
        pre = cur;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
