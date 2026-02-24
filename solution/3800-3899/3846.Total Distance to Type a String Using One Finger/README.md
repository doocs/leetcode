---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3846.Total%20Distance%20to%20Type%20a%20String%20Using%20One%20Finger/README.md
---

<!-- problem:start -->

# [3846. 使用单指输入字符串的总距离 🔒](https://leetcode.cn/problems/total-distance-to-type-a-string-using-one-finger)

[English Version](/solution/3800-3899/3846.Total%20Distance%20to%20Type%20a%20String%20Using%20One%20Finger/README_EN.md)

## 题目描述

<!-- description:start -->

有一个特殊的键盘，其按键排列成如下矩形网格。

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

<p>给定一个只包含小写英文字母的字符串&nbsp;<code>s</code>。返回一个整数，表示使用仅一个手指输入字符串 <code>s</code> 的总距离。手指初始位置在字母键 <code>'a'</code> 上。</p>

<p>两个位于&nbsp;<code>(r1, c1)</code> 和&nbsp;<code>(r2, c2)</code>&nbsp;的按键距离是&nbsp;<code>|r1 - r2| + |c1 - c2|</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "hello"</span></p>

<p><span class="example-io"><b>输出：</b>17</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>你的手指从&nbsp;<code>'a'</code>&nbsp;开始，位于&nbsp;<code>(1, 0)</code>。</li>
	<li>移动到&nbsp;<code>'h'</code>，位于&nbsp;<code>(1, 5)</code>。距离是&nbsp;<code>|1 - 1| + |0 - 5| = 5</code>。</li>
	<li>移动到 <code>'e'</code>，位于&nbsp;<code>(0, 2)</code>。距离是&nbsp;<code>|1 - 0| + |5 - 2| = 4</code>。</li>
	<li>移动到 <code>'l'</code>，位于&nbsp;<code>(1, 8)</code>。距离是&nbsp;<code>|0 - 1| + |2 - 8| = 7</code>。</li>
	<li>移动到 <code>'l'</code>，位于&nbsp;<code>(1, 8)</code>。距离是&nbsp;<code>|1 - 1| + |8 - 8| = 0</code>。</li>
	<li>移动到 <code>'o'</code>，位于&nbsp;<code>(0, 8)</code>。距离是&nbsp;<code>|1 - 0| + |8 - 8| = 1</code>。</li>
	<li>总距离是&nbsp;<code>5 + 4 + 7 + 0 + 1 = 17</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "a"</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>你的手指从&nbsp;<code>'a'</code>&nbsp;开始，位于&nbsp;<code>(1, 0)</code>。</li>
	<li>移动到&nbsp;<code>'a'</code>，位于&nbsp;<code>(1, 0)</code>。距离是&nbsp;<code>|1 - 1| + |0 - 0| = 0</code>。</li>
	<li>总距离为 0。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们定义一个哈希表 $\textit{pos}$，用来存储每个字符在键盘上的位置。对于字符串 $s$ 中的每个字符，我们计算从上一个字符到当前字符的距离，并将其累加到答案中。最后返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。空间复杂度 $O(|\Sigma|)$，其中 $\Sigma$ 是字符集，这里是 26 个小写英文字母。

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
