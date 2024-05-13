# [3144. 分割字符频率相等的最少子字符串](https://leetcode.cn/problems/minimum-substring-partition-of-equal-character-frequency)

[English Version](/solution/3100-3199/3144.Minimum%20Substring%20Partition%20of%20Equal%20Character%20Frequency/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，你需要将它分割成一个或者更多的&nbsp;<strong>平衡</strong>&nbsp;子字符串。比方说，<code>s == "ababcc"</code>&nbsp;那么&nbsp;<code>("abab", "c", "c")</code>&nbsp;，<code>("ab", "abc", "c")</code>&nbsp;和&nbsp;<code>("ababcc")</code>&nbsp;都是合法分割，但是&nbsp;<code>("a", <strong>"bab"</strong>, "cc")</code>&nbsp;，<code>(<strong>"aba"</strong>, "bc", "c")</code>&nbsp;和&nbsp;<code>("ab", <strong>"abcc"</strong>)</code>&nbsp;不是，不平衡的子字符串用粗体表示。</p>

<p>请你返回 <code>s</code>&nbsp;<strong>最少</strong> 能分割成多少个平衡子字符串。</p>

<p><b>注意：</b>一个 <strong>平衡</strong>&nbsp;字符串指的是字符串中所有字符出现的次数都相同。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "fabccddg"</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<p>我们可以将 <code>s</code>&nbsp;分割成 3 个子字符串：<code>("fab, "ccdd", "g")</code>&nbsp;或者&nbsp;<code>("fabc", "cd", "dg")</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "abababaccddb"</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p>我们可以将&nbsp;<code>s</code>&nbsp;分割成 2 个子字符串：<code>("abab", "abaccddb")</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

### 方法一：记忆化搜索 + 哈希表

我们设计一个函数 $\text{dfs}(i)$，表示从字符串 $s[i]$ 开始分割的最少子字符串数量。那么答案就是 $\text{dfs}(0)$。

函数 $\text{dfs}(i)$ 的计算过程如下：

如果 $i \geq n$，表示已经处理完了所有字符，返回 $0$。

否则，我们维护一个哈希表 $\text{cnt}$，表示当前子字符串中每个字符出现的次数。另外，我们还维护一个哈希表 $\text{freq}$，表示每个字符出现的次数的频率。

然后我们枚举 $j$ 从 $i$ 到 $n-1$，表示当前子字符串的结束位置。对于每个 $j$，我们更新 $\text{cnt}$ 和 $\text{freq}$，然后判断 $\text{freq}$ 的大小是否为 $1$，如果是的话，我们可以从 $j+1$ 开始分割，此时答案为 $1 + \text{dfs}(j+1)$，我们取所有 $j$ 中答案的最小值作为函数的返回值。

为了避免重复计算，我们使用记忆化搜索。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def minimumSubstringsInPartition(self, s: str) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= n:
                return 0
            cnt = defaultdict(int)
            freq = defaultdict(int)
            ans = n - i
            for j in range(i, n):
                if cnt[s[j]]:
                    freq[cnt[s[j]]] -= 1
                    if not freq[cnt[s[j]]]:
                        freq.pop(cnt[s[j]])
                cnt[s[j]] += 1
                freq[cnt[s[j]]] += 1
                if len(freq) == 1 and (t := 1 + dfs(j + 1)) < ans:
                    ans = t
            return ans

        n = len(s)
        return dfs(0)
```

```java
class Solution {
    private int n;
    private char[] s;
    private Integer[] f;

    public int minimumSubstringsInPartition(String s) {
        n = s.length();
        f = new Integer[n];
        this.s = s.toCharArray();
        return dfs(0);
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int[] cnt = new int[26];
        Map<Integer, Integer> freq = new HashMap<>(26);
        int ans = n - i;
        for (int j = i; j < n; ++j) {
            int k = s[j] - 'a';
            if (cnt[k] > 0) {
                if (freq.merge(cnt[k], -1, Integer::sum) == 0) {
                    freq.remove(cnt[k]);
                }
            }
            ++cnt[k];
            freq.merge(cnt[k], 1, Integer::sum);
            if (freq.size() == 1) {
                ans = Math.min(ans, 1 + dfs(j + 1));
            }
        }
        return f[i] = ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumSubstringsInPartition(string s) {
        int n = s.size();
        int f[n];
        memset(f, -1, sizeof(f));
        function<int(int)> dfs = [&](int i) {
            if (i >= n) {
                return 0;
            }
            if (f[i] != -1) {
                return f[i];
            }
            f[i] = n - i;
            int cnt[26]{};
            unordered_map<int, int> freq;
            for (int j = i; j < n; ++j) {
                int k = s[j] - 'a';
                if (cnt[k]) {
                    freq[cnt[k]]--;
                    if (freq[cnt[k]] == 0) {
                        freq.erase(cnt[k]);
                    }
                }
                ++cnt[k];
                ++freq[cnt[k]];
                if (freq.size() == 1) {
                    f[i] = min(f[i], 1 + dfs(j + 1));
                }
            }
            return f[i];
        };
        return dfs(0);
    }
};
```

```go
func minimumSubstringsInPartition(s string) int {
	n := len(s)
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] != -1 {
			return f[i]
		}
		cnt := [26]int{}
		freq := map[int]int{}
		f[i] = n - i
		for j := i; j < n; j++ {
			k := int(s[j] - 'a')
			if cnt[k] > 0 {
				freq[cnt[k]]--
				if freq[cnt[k]] == 0 {
					delete(freq, cnt[k])
				}
			}
			cnt[k]++
			freq[cnt[k]]++
			if len(freq) == 1 {
				f[i] = min(f[i], 1+dfs(j+1))
			}
		}
		return f[i]
	}
	return dfs(0)
}
```

```ts
function minimumSubstringsInPartition(s: string): number {
    const n = s.length;
    const f: number[] = Array(n).fill(-1);
    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i] !== -1) {
            return f[i];
        }
        const cnt: Map<number, number> = new Map();
        const freq: Map<number, number> = new Map();
        f[i] = n - i;
        for (let j = i; j < n; ++j) {
            const k = s.charCodeAt(j) - 97;
            if (freq.has(cnt.get(k)!)) {
                freq.set(cnt.get(k)!, freq.get(cnt.get(k)!)! - 1);
                if (freq.get(cnt.get(k)!) === 0) {
                    freq.delete(cnt.get(k)!);
                }
            }
            cnt.set(k, (cnt.get(k) || 0) + 1);
            freq.set(cnt.get(k)!, (freq.get(cnt.get(k)!) || 0) + 1);
            if (freq.size === 1) {
                f[i] = Math.min(f[i], 1 + dfs(j + 1));
            }
        }
        return f[i];
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- end -->
