---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3003.Maximize%20the%20Number%20of%20Partitions%20After%20Operations/README.md
rating: 3039
source: 第 379 场周赛 Q4
tags:
    - 位运算
    - 字符串
    - 动态规划
    - 状态压缩
---

<!-- problem:start -->

# [3003. 执行操作后的最大分割数量](https://leetcode.cn/problems/maximize-the-number-of-partitions-after-operations)

[English Version](/solution/3000-3099/3003.Maximize%20the%20Number%20of%20Partitions%20After%20Operations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串&nbsp;<code>s</code>&nbsp;和一个整数&nbsp;<code>k</code>。</p>

<p>你需要执行以下分割操作，直到字符串&nbsp;<code>s&nbsp;</code>变为&nbsp;<strong>空</strong>：</p>

<ul>
	<li>选择&nbsp;<code>s</code>&nbsp;的最长&nbsp;<strong>前缀</strong>，该前缀最多包含&nbsp;<code>k&nbsp;</code>个&nbsp;<strong>不同&nbsp;</strong>字符。</li>
	<li><strong>删除&nbsp;</strong>这个前缀，并将分割数量加一。如果有剩余字符，它们在&nbsp;<code>s</code>&nbsp;中保持原来的顺序。</li>
</ul>

<p>执行操作之 <strong>前</strong> ，你可以将&nbsp;<code>s</code>&nbsp;中&nbsp;<strong>至多一处 </strong>下标的对应字符更改为另一个小写英文字母。</p>

<p>在最优选择情形下改变至多一处下标对应字符后，用整数表示并返回操作结束时得到的 <strong>最大</strong> 分割数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = "accca", k = 2</span></p>

<p><strong>输出：</strong><span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>最好的方式是把&nbsp;<code>s[2]</code>&nbsp;变为除了 a 和 c 之外的东西，比如&nbsp;b。然后它变成了&nbsp;<code>"acbca"</code>。</p>

<p>然后我们执行以下操作：</p>

<ol>
	<li>最多包含 2 个不同字符的最长前缀是 <code>"ac"</code>，我们删除它然后&nbsp;<code>s</code> 变为&nbsp;<code>"bca"</code>。</li>
	<li>现在最多包含 2 个不同字符的最长前缀是&nbsp;<code>"bc"</code>，所以我们删除它然后&nbsp;<code>s</code> 变为&nbsp;<code>"a"</code>。</li>
	<li>最后，我们删除&nbsp;<code>"a"</code>&nbsp;并且&nbsp;<code>s</code>&nbsp;变成空串，所以该过程结束。</li>
</ol>

<p>进行操作时，字符串被分成 3 个部分，所以答案是 3。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = "aabaab", k = 3</span></p>

<p><strong>输出：</strong><span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>一开始&nbsp;<code>s</code>&nbsp;包含 2 个不同的字符，所以无论我们改变哪个，&nbsp;它最多包含 3 个不同字符，因此最多包含 3 个不同字符的最长前缀始终是所有字符，因此答案是 1。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = "xxyz", k = 1</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p>最好的方式是将&nbsp;<code>s[0]</code>&nbsp;或&nbsp;<code>s[1]</code>&nbsp;变为&nbsp;<code>s</code>&nbsp;中字符以外的东西，例如将&nbsp;<code>s[0]</code>&nbsp;变为&nbsp;<code>w</code>。</p>

<p>然后&nbsp;<code>s</code>&nbsp;变为&nbsp;<code>"wxyz"</code>，包含 4 个不同的字符，所以当&nbsp;<code>k</code>&nbsp;为 1，它将分为 4 个部分。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
	<li><code>1 &lt;= k &lt;= 26</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxPartitionsAfterOperations(self, s: str, k: int) -> int:
        @cache
        def dfs(i: int, cur: int, t: int) -> int:
            if i >= n:
                return 1
            v = 1 << (ord(s[i]) - ord("a"))
            nxt = cur | v
            if nxt.bit_count() > k:
                ans = dfs(i + 1, v, t) + 1
            else:
                ans = dfs(i + 1, nxt, t)
            if t:
                for j in range(26):
                    nxt = cur | (1 << j)
                    if nxt.bit_count() > k:
                        ans = max(ans, dfs(i + 1, 1 << j, 0) + 1)
                    else:
                        ans = max(ans, dfs(i + 1, nxt, 0))
            return ans

        n = len(s)
        return dfs(0, 0, 1)
```

#### Java

```java
class Solution {
    private Map<List<Integer>, Integer> f = new HashMap<>();
    private String s;
    private int k;

    public int maxPartitionsAfterOperations(String s, int k) {
        this.s = s;
        this.k = k;
        return dfs(0, 0, 1);
    }

    private int dfs(int i, int cur, int t) {
        if (i >= s.length()) {
            return 1;
        }
        var key = List.of(i, cur, t);
        if (f.containsKey(key)) {
            return f.get(key);
        }
        int v = 1 << (s.charAt(i) - 'a');
        int nxt = cur | v;
        int ans = Integer.bitCount(nxt) > k ? dfs(i + 1, v, t) + 1 : dfs(i + 1, nxt, t);
        if (t > 0) {
            for (int j = 0; j < 26; ++j) {
                nxt = cur | (1 << j);
                if (Integer.bitCount(nxt) > k) {
                    ans = Math.max(ans, dfs(i + 1, 1 << j, 0) + 1);
                } else {
                    ans = Math.max(ans, dfs(i + 1, nxt, 0));
                }
            }
        }
        f.put(key, ans);
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxPartitionsAfterOperations(string s, int k) {
        int n = s.size();
        unordered_map<long long, int> f;
        function<int(int, int, int)> dfs = [&](int i, int cur, int t) {
            if (i >= n) {
                return 1;
            }
            long long key = (long long) i << 32 | cur << 1 | t;
            if (f.count(key)) {
                return f[key];
            }
            int v = 1 << (s[i] - 'a');
            int nxt = cur | v;
            int ans = __builtin_popcount(nxt) > k ? dfs(i + 1, v, t) + 1 : dfs(i + 1, nxt, t);
            if (t) {
                for (int j = 0; j < 26; ++j) {
                    nxt = cur | (1 << j);
                    if (__builtin_popcount(nxt) > k) {
                        ans = max(ans, dfs(i + 1, 1 << j, 0) + 1);
                    } else {
                        ans = max(ans, dfs(i + 1, nxt, 0));
                    }
                }
            }
            return f[key] = ans;
        };
        return dfs(0, 0, 1);
    }
};
```

#### Go

```go
func maxPartitionsAfterOperations(s string, k int) int {
	n := len(s)
	type tuple struct{ i, cur, t int }
	f := map[tuple]int{}
	var dfs func(i, cur, t int) int
	dfs = func(i, cur, t int) int {
		if i >= n {
			return 1
		}
		key := tuple{i, cur, t}
		if v, ok := f[key]; ok {
			return v
		}
		v := 1 << (s[i] - 'a')
		nxt := cur | v
		var ans int
		if bits.OnesCount(uint(nxt)) > k {
			ans = dfs(i+1, v, t) + 1
		} else {
			ans = dfs(i+1, nxt, t)
		}
		if t > 0 {
			for j := 0; j < 26; j++ {
				nxt = cur | (1 << j)
				if bits.OnesCount(uint(nxt)) > k {
					ans = max(ans, dfs(i+1, 1<<j, 0)+1)
				} else {
					ans = max(ans, dfs(i+1, nxt, 0))
				}
			}
		}
		f[key] = ans
		return ans
	}
	return dfs(0, 0, 1)
}
```

#### TypeScript

```ts
function maxPartitionsAfterOperations(s: string, k: number): number {
    const n = s.length;
    const f: Map<bigint, number> = new Map();
    const dfs = (i: number, cur: number, t: number): number => {
        if (i >= n) {
            return 1;
        }
        const key = (BigInt(i) << 27n) | (BigInt(cur) << 1n) | BigInt(t);
        if (f.has(key)) {
            return f.get(key)!;
        }
        const v = 1 << (s.charCodeAt(i) - 97);
        let nxt = cur | v;
        let ans = 0;
        if (bitCount(nxt) > k) {
            ans = dfs(i + 1, v, t) + 1;
        } else {
            ans = dfs(i + 1, nxt, t);
        }
        if (t) {
            for (let j = 0; j < 26; ++j) {
                nxt = cur | (1 << j);
                if (bitCount(nxt) > k) {
                    ans = Math.max(ans, dfs(i + 1, 1 << j, 0) + 1);
                } else {
                    ans = Math.max(ans, dfs(i + 1, nxt, 0));
                }
            }
        }
        f.set(key, ans);
        return ans;
    };
    return dfs(0, 0, 1);
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
