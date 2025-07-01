---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3598.Longest%20Common%20Prefix%20Between%20Adjacent%20Strings%20After%20Removals/README.md
tags:
    - 数组
    - 字符串
---

<!-- problem:start -->

# [3598. 相邻字符串之间的最长公共前缀](https://leetcode.cn/problems/longest-common-prefix-between-adjacent-strings-after-removals)

[English Version](/solution/3500-3599/3598.Longest%20Common%20Prefix%20Between%20Adjacent%20Strings%20After%20Removals/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串数组 <code>words</code>，对于范围 <code>[0, words.length - 1]</code> 内的每个下标&nbsp;<code>i</code>，执行以下步骤：</p>

<ul>
	<li>从 <code>words</code> 数组中移除下标&nbsp;<code>i</code> 处的元素。</li>
	<li>计算修改后的数组中所有&nbsp;<strong>相邻对&nbsp;</strong>之间的&nbsp;<strong>最长公共前缀&nbsp;</strong>的长度。</li>
</ul>

<p>返回一个数组 <code>answer</code>，其中 <code>answer[i]</code> 是移除下标&nbsp;<code>i</code> 后，相邻对之间最长公共前缀的长度。如果 <strong>不存在&nbsp;</strong>相邻对，或者&nbsp;<strong>不存在&nbsp;</strong>公共前缀，则 <code>answer[i]</code> 应为 0。</p>

<p>字符串的前缀是从字符串的开头开始延伸到任意位置的子字符串。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">words = ["jump","run","run","jump","run"]</span></p>

<p><strong>输出：</strong> <span class="example-io">[3,0,0,3,3]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>移除下标&nbsp;0：
	<ul>
		<li><code>words</code> 变为 <code>["run", "run", "jump", "run"]</code></li>
		<li>最长的相邻对是 <code>["run", "run"]</code>，其公共前缀为 <code>"run"</code>（长度为 3）</li>
	</ul>
	</li>
	<li>移除下标&nbsp;1：
	<ul>
		<li><code>words</code> 变为 <code>["jump", "run", "jump", "run"]</code></li>
		<li>没有相邻对有公共前缀（长度为 0）</li>
	</ul>
	</li>
	<li>移除下标&nbsp;2：
	<ul>
		<li><code>words</code> 变为 <code>["jump", "run", "jump", "run"]</code></li>
		<li>没有相邻对有公共前缀（长度为 0）</li>
	</ul>
	</li>
	<li>移除下标&nbsp;3：
	<ul>
		<li><code>words</code> 变为 <code>["jump", "run", "run", "run"]</code></li>
		<li>最长的相邻对是 <code>["run", "run"]</code>，其公共前缀为 <code>"run"</code>（长度为 3）</li>
	</ul>
	</li>
	<li>移除下标&nbsp;4：
	<ul>
		<li><code>words</code> 变为 <code>["jump", "run", "run", "jump"]</code></li>
		<li>最长的相邻对是 <code>["run", "run"]</code>，其公共前缀为 <code>"run"</code>（长度为 3）</li>
	</ul>
	</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">words = ["dog","racer","car"]</span></p>

<p><strong>输出：</strong> <span class="example-io">[0,0,0]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>移除任意下标都会导致答案为 0。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10<sup>4</sup></code></li>
	<li><code>words[i]</code> 仅由小写英文字母组成。</li>
	<li><code>words[i]</code> 的长度总和不超过 <code>10<sup>5</sup></code>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：有序集合

我们定义一个函数 $\textit{calc}(s, t)$，它计算字符串 $s$ 和 $t$ 的最长公共前缀的长度。我们可以使用有序集合来维护所有相邻字符串对的最长公共前缀长度。

定义一个函数 $\textit{add}(i, j)$，它将下标 $i$ 和 $j$ 处的字符串对的最长公共前缀长度添加到有序集合中。定义一个函数 $\textit{remove}(i, j)$，它从有序集合中移除下标 $i$ 和 $j$ 处的字符串对的最长公共前缀长度。

我们首先计算所有相邻字符串对的最长公共前缀长度，并将其存储在有序集合中。然后，我们遍历每个下标 $i$，执行以下操作：

1. 移除下标 $i$ 和 $i + 1$ 处的字符串对的最长公共前缀长度。
2. 移除下标 $i - 1$ 和 $i$ 处的字符串对的最长公共前缀长度。
3. 添加下标 $i - 1$ 和 $i + 1$ 处的字符串对的最长公共前缀长度。
4. 将当前有序集合中的最大值（如果存在且大于 0）添加到答案中。
5. 移除下标 $i - 1$ 和 $i + 1$ 处的字符串对的最长公共前缀长度。
6. 添加下标 $i - 1$ 和 $i$ 处的字符串对的最长公共前缀长度。
7. 添加下标 $i$ 和 $i + 1$ 处的字符串对的最长公共前缀长度。

这样，我们可以在每次移除一个字符串后，快速计算出相邻字符串对的最长公共前缀长度。

时间复杂度 $O(L + n \times \log n)$，空间复杂度 $O(n)$，其中 $L$ 是所有字符串的总长度，而 $n$ 是字符串的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestCommonPrefix(self, words: List[str]) -> List[int]:
        @cache
        def calc(s: str, t: str) -> int:
            k = 0
            for a, b in zip(s, t):
                if a != b:
                    break
                k += 1
            return k

        def add(i: int, j: int):
            if 0 <= i < n and 0 <= j < n:
                sl.add(calc(words[i], words[j]))

        def remove(i: int, j: int):
            if 0 <= i < n and 0 <= j < n:
                sl.remove(calc(words[i], words[j]))

        n = len(words)
        sl = SortedList(calc(a, b) for a, b in pairwise(words))
        ans = []
        for i in range(n):
            remove(i, i + 1)
            remove(i - 1, i)
            add(i - 1, i + 1)
            ans.append(sl[-1] if sl and sl[-1] > 0 else 0)
            remove(i - 1, i + 1)
            add(i - 1, i)
            add(i, i + 1)
        return ans
```

#### Java

```java
class Solution {
    private final TreeMap<Integer, Integer> tm = new TreeMap<>();
    private String[] words;
    private int n;

    public int[] longestCommonPrefix(String[] words) {
        n = words.length;
        this.words = words;
        for (int i = 0; i + 1 < n; ++i) {
            tm.merge(calc(words[i], words[i + 1]), 1, Integer::sum);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            remove(i, i + 1);
            remove(i - 1, i);
            add(i - 1, i + 1);
            ans[i] = !tm.isEmpty() && tm.lastKey() > 0 ? tm.lastKey() : 0;
            remove(i - 1, i + 1);
            add(i - 1, i);
            add(i, i + 1);
        }
        return ans;
    }

    private void add(int i, int j) {
        if (i >= 0 && i < n && j >= 0 && j < n) {
            tm.merge(calc(words[i], words[j]), 1, Integer::sum);
        }
    }

    private void remove(int i, int j) {
        if (i >= 0 && i < n && j >= 0 && j < n) {
            int x = calc(words[i], words[j]);
            if (tm.merge(x, -1, Integer::sum) == 0) {
                tm.remove(x);
            }
        }
    }

    private int calc(String s, String t) {
        int m = Math.min(s.length(), t.length());
        for (int k = 0; k < m; ++k) {
            if (s.charAt(k) != t.charAt(k)) {
                return k;
            }
        }
        return m;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> longestCommonPrefix(vector<string>& words) {
        multiset<int> ms;
        int n = words.size();
        auto calc = [&](const string& s, const string& t) {
            int m = min(s.size(), t.size());
            for (int k = 0; k < m; ++k) {
                if (s[k] != t[k]) {
                    return k;
                }
            }
            return m;
        };
        for (int i = 0; i + 1 < n; ++i) {
            ms.insert(calc(words[i], words[i + 1]));
        }
        vector<int> ans(n);
        auto add = [&](int i, int j) {
            if (i >= 0 && i < n && j >= 0 && j < n) {
                ms.insert(calc(words[i], words[j]));
            }
        };
        auto remove = [&](int i, int j) {
            if (i >= 0 && i < n && j >= 0 && j < n) {
                int x = calc(words[i], words[j]);
                auto it = ms.find(x);
                if (it != ms.end()) {
                    ms.erase(it);
                }
            }
        };
        for (int i = 0; i < n; ++i) {
            remove(i, i + 1);
            remove(i - 1, i);
            add(i - 1, i + 1);
            ans[i] = ms.empty() ? 0 : *ms.rbegin();
            remove(i - 1, i + 1);
            add(i - 1, i);
            add(i, i + 1);
        }
        return ans;
    }
};
```

#### Go

```go
func longestCommonPrefix(words []string) []int {
	n := len(words)
	tm := treemap.NewWithIntComparator()

	calc := func(s, t string) int {
		m := min(len(s), len(t))
		for k := 0; k < m; k++ {
			if s[k] != t[k] {
				return k
			}
		}
		return m
	}

	add := func(i, j int) {
		if i >= 0 && i < n && j >= 0 && j < n {
			x := calc(words[i], words[j])
			if v, ok := tm.Get(x); ok {
				tm.Put(x, v.(int)+1)
			} else {
				tm.Put(x, 1)
			}
		}
	}

	remove := func(i, j int) {
		if i >= 0 && i < n && j >= 0 && j < n {
			x := calc(words[i], words[j])
			if v, ok := tm.Get(x); ok {
				if v.(int) == 1 {
					tm.Remove(x)
				} else {
					tm.Put(x, v.(int)-1)
				}
			}
		}
	}

	for i := 0; i+1 < n; i++ {
		add(i, i+1)
	}

	ans := make([]int, n)
	for i := 0; i < n; i++ {
		remove(i, i+1)
		remove(i-1, i)
		add(i-1, i+1)

		if !tm.Empty() {
			if maxKey, _ := tm.Max(); maxKey.(int) > 0 {
				ans[i] = maxKey.(int)
			}
		}

		remove(i-1, i+1)
		add(i-1, i)
		add(i, i+1)
	}

	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
