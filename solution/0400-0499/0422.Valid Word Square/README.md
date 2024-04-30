# [422. 有效的单词方块 🔒](https://leetcode.cn/problems/valid-word-square)

[English Version](/solution/0400-0499/0422.Valid%20Word%20Square/README_EN.md)

<!-- tags:数组,矩阵 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个单词序列，判断其是否形成了一个有效的单词方块。</p>

<p>有效的单词方块是指此由单词序列组成的文字方块的&nbsp;第 k 行 和&nbsp;第 k 列 (0 ≤ <em>k</em> &lt; max(行数, 列数)) 所显示的字符串完全相同。</p>

<p><strong>注意：</strong></p>

<ol>
	<li>给定的单词数大于等于 1 且不超过 500。</li>
	<li>单词长度大于等于 1 且不超过 500。</li>
	<li>每个单词只包含小写英文字母&nbsp;<code>a-z</code>。</li>
</ol>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0422.Valid%20Word%20Square/images/1713840723-rPoAKZ-image.png" style="width: 333px; height: 333px;" /></strong></p>

<pre>
<strong>输入：</strong>
[
  "abcd",
  "bnrt",
  "crmy",
  "dtye"
]

<strong>输出：</strong>
true

<strong>解释：</strong>
第 1 行和第 1 列都是 "abcd"。
第 2 行和第 2 列都是 "bnrt"。
第 3 行和第 3 列都是 "crmy"。
第 4 行和第 4 列都是 "dtye"。

因此，这是一个有效的单词方块。
</pre>

<p>&nbsp;</p>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0422.Valid%20Word%20Square/images/1713840732-EvBDzU-image.png" style="width: 333px; height: 333px;" /></strong></p>

<pre>
<strong>输入：</strong>
[
  "abcd",
  "bnrt",
  "crm",
  "dt"
]

<strong>输出：</strong>
true

<strong>解释：</strong>
第 1 行和第 1 列都是 "abcd"。
第 2 行和第 2 列都是 "bnrt"。
第 3 行和第 3 列都是 "crm"。
第 4 行和第 4 列都是 "dt"。

因此，这是一个有效的单词方块。
</pre>

<p>&nbsp;</p>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0422.Valid%20Word%20Square/images/1713840738-tdOYTB-image.png" style="width: 333px; height: 333px;" /></strong></p>

<pre>
<strong>输入：</strong>
[
  "ball",
  "area",
  "read",
  "lady"
]

<strong>输出：</strong>
false

<strong>解释：</strong>
第 3 行是 "read" ，然而第 3 列是 "lead"。

因此，这 <strong>不是</strong> 一个有效的单词方块。
</pre>

<p>&nbsp;</p>

## 解法

### 方法一：遍历检查

我们观察发现，只要不满足 $words[i][j] = words[j][i]$，就可以直接返回 `false`。

因此，我们只需要遍历每一行，然后检查每一行是否满足 $words[i][j] = words[j][i]$ 即可。注意，如果下标越界，也直接返回 `false`。

时间复杂度 $O(n^2)$，其中 $n$ 是 $words$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def validWordSquare(self, words: List[str]) -> bool:
        m = len(words)
        n = max(len(w) for w in words)
        if m != n:
            return False
        for j in range(n):
            if words[j] != "".join(w[j] for w in words if j < len(w)):
                return False
        return True
```

```java
class Solution {
    public boolean validWordSquare(List<String> words) {
        int m = words.size();
        for (int i = 0; i < m; ++i) {
            int n = words.get(i).length();
            for (int j = 0; j < n; ++j) {
                if (j >= m || i >= words.get(j).length()) {
                    return false;
                }
                if (words.get(i).charAt(j) != words.get(j).charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    bool validWordSquare(vector<string>& words) {
        int m = words.size();
        for (int i = 0; i < m; ++i) {
            int n = words[i].size();
            for (int j = 0; j < n; ++j) {
                if (j >= m || i >= words[j].size() || words[i][j] != words[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }
};
```

```go
func validWordSquare(words []string) bool {
	m := len(words)
	for i, w := range words {
		for j := range w {
			if j >= m || i >= len(words[j]) || w[j] != words[j][i] {
				return false
			}
		}
	}
	return true
}
```

```ts
function validWordSquare(words: string[]): boolean {
    const m = words.length;
    for (let i = 0; i < m; ++i) {
        const n = words[i].length;
        for (let j = 0; j < n; ++j) {
            if (j >= m || i >= words[j].length || words[i][j] !== words[j][i]) {
                return false;
            }
        }
    }
    return true;
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def validWordSquare(self, words: List[str]) -> bool:
        m = len(words)
        for i, w in enumerate(words):
            for j, c in enumerate(w):
                if j >= m or i >= len(words[j]) or c != words[j][i]:
                    return False
        return True
```

<!-- tabs:end -->

<!-- end -->
