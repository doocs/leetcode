---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2953.Count%20Complete%20Substrings/README.md
rating: 2449
source: 第 374 场周赛 Q3
tags:
    - 哈希表
    - 字符串
    - 滑动窗口
---

<!-- problem:start -->

# [2953. 统计完全子字符串](https://leetcode.cn/problems/count-complete-substrings)

[English Version](/solution/2900-2999/2953.Count%20Complete%20Substrings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串&nbsp;<code>word</code>&nbsp;和一个整数 <code>k</code>&nbsp;。</p>

<p>如果&nbsp;<code>word</code>&nbsp;的一个子字符串 <code>s</code>&nbsp;满足以下条件，我们称它是 <strong>完全字符串：</strong></p>

<ul>
	<li><code>s</code>&nbsp;中每个字符 <strong>恰好</strong>&nbsp;出现 <code>k</code>&nbsp;次。</li>
	<li>相邻字符在字母表中的顺序 <strong>至多</strong>&nbsp;相差&nbsp;<code>2</code>&nbsp;。也就是说，<code>s</code>&nbsp;中两个相邻字符&nbsp;<code>c1</code> 和&nbsp;<code>c2</code>&nbsp;，它们在字母表中的位置相差<strong>&nbsp;至多</strong>&nbsp;为 <code>2</code> 。</li>
</ul>

<p>请你返回 <code>word</code>&nbsp;中 <strong>完全</strong>&nbsp;子字符串的数目。</p>

<p><strong>子字符串</strong>&nbsp;指的是一个字符串中一段连续 <strong>非空</strong>&nbsp;的字符序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>word = "igigee", k = 2
<b>输出：</b>3
<b>解释：</b>完全子字符串需要满足每个字符恰好出现 2 次，且相邻字符相差至多为 2 ：<em><strong>igig</strong></em>ee, igig<strong style="font-style: italic;">ee</strong>, <em><strong>igigee</strong>&nbsp;。</em>
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>word = "aaabbbccc", k = 3
<b>输出：</b>6
<b>解释：</b>完全子字符串需要满足每个字符恰好出现 3 次，且相邻字符相差至多为 2 ：<em><strong>aaa</strong></em>bbbccc, aaa<em><strong>bbb</strong></em>ccc, aaabbb<em><strong>ccc</strong></em>, <em><strong>aaabbb</strong></em>ccc, aaa<em><strong>bbbccc</strong></em>, <em><strong>aaabbbccc </strong></em>。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 10<sup>5</sup></code></li>
	<li><code>word</code>&nbsp;只包含小写英文字母。</li>
	<li><code>1 &lt;= k &lt;= word.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举字符种类数 + 滑动窗口

根据题目描述中的条件 $2$，我们可以发现，一个完全字符串中，相邻两个字符之差不超过 $2$。因此，我们遍历字符串 $word$，可以利用双指针把 $word$ 分割成若干个子字符串，这些子字符串中的字符种类数不超过 $26$，且相邻字符之差不超过 $2$。接下来，我们只需要在每个子字符串中，统计每个字符都出现 $k$ 次的子字符串的个数即可。

我们定义一个函数 $f(s)$，它的功能是统计字符串 $s$ 中每个字符都出现 $k$ 次的子字符串的个数。由于 $s$ 中的字符种类数不超过 $26$，因此我们可以枚举每个字符种类数 $i$，其中 $1 \le i \le 26$，那么每个字符种类数为 $i$ 的子字符串的长度为 $l = i \times k$。

我们可以用一个数组或哈希表 $cnt$ 维护一个长度为 $l$ 的滑动窗口中每个字符出现的次数，用另一个哈希表 $freq$ 维护每个次数出现的次数。如果 $freq[k] = i$，即有 $i$ 个字符都出现了 $k$ 次，那么我们就找到了一个满足条件的子字符串。我们可以用双指针维护这个滑动窗口，每次移动右指针时，我们将右指针指向的字符出现的次数加一，并更新 $freq$ 数组；每次移动左指针时，我们将左指针指向的字符出现的次数减一，并更新 $freq$ 数组。在每次移动指针后，我们都判断 $freq[k]$ 是否等于 $i$，如果等于则说明我们找到了一个满足条件的子字符串。

时间复杂度 $O(n \times |\Sigma|)$，空间复杂度 $O(|\Sigma|)$，其中 $n$ 是字符串 $word$ 的长度；而 $\Sigma$ 是字符集的大小，本题中字符集为小写英文字母，因此 $|\Sigma| = 26$。

<!-- tabs:start -->

```python
class Solution:
    def countCompleteSubstrings(self, word: str, k: int) -> int:
        def f(s: str) -> int:
            m = len(s)
            ans = 0
            for i in range(1, 27):
                l = i * k
                if l > m:
                    break
                cnt = Counter(s[:l])
                freq = Counter(cnt.values())
                ans += freq[k] == i
                for j in range(l, m):
                    freq[cnt[s[j]]] -= 1
                    cnt[s[j]] += 1
                    freq[cnt[s[j]]] += 1

                    freq[cnt[s[j - l]]] -= 1
                    cnt[s[j - l]] -= 1
                    freq[cnt[s[j - l]]] += 1

                    ans += freq[k] == i
            return ans

        n = len(word)
        ans = i = 0
        while i < n:
            j = i + 1
            while j < n and abs(ord(word[j]) - ord(word[j - 1])) <= 2:
                j += 1
            ans += f(word[i:j])
            i = j
        return ans
```

```java
class Solution {
    public int countCompleteSubstrings(String word, int k) {
        int n = word.length();
        int ans = 0;
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && Math.abs(word.charAt(j) - word.charAt(j - 1)) <= 2) {
                ++j;
            }
            ans += f(word.substring(i, j), k);
            i = j;
        }
        return ans;
    }

    private int f(String s, int k) {
        int m = s.length();
        int ans = 0;
        for (int i = 1; i <= 26 && i * k <= m; ++i) {
            int l = i * k;
            int[] cnt = new int[26];
            for (int j = 0; j < l; ++j) {
                ++cnt[s.charAt(j) - 'a'];
            }
            Map<Integer, Integer> freq = new HashMap<>();
            for (int x : cnt) {
                if (x > 0) {
                    freq.merge(x, 1, Integer::sum);
                }
            }
            if (freq.getOrDefault(k, 0) == i) {
                ++ans;
            }
            for (int j = l; j < m; ++j) {
                int a = s.charAt(j) - 'a';
                int b = s.charAt(j - l) - 'a';
                freq.merge(cnt[a], -1, Integer::sum);
                ++cnt[a];
                freq.merge(cnt[a], 1, Integer::sum);

                freq.merge(cnt[b], -1, Integer::sum);
                --cnt[b];
                freq.merge(cnt[b], 1, Integer::sum);
                if (freq.getOrDefault(k, 0) == i) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countCompleteSubstrings(string word, int k) {
        int n = word.length();
        int ans = 0;
        auto f = [&](string s) {
            int m = s.length();
            int ans = 0;
            for (int i = 1; i <= 26 && i * k <= m; ++i) {
                int l = i * k;
                int cnt[26]{};
                for (int j = 0; j < l; ++j) {
                    ++cnt[s[j] - 'a'];
                }
                unordered_map<int, int> freq;
                for (int x : cnt) {
                    if (x > 0) {
                        freq[x]++;
                    }
                }
                if (freq[k] == i) {
                    ++ans;
                }
                for (int j = l; j < m; ++j) {
                    int a = s[j] - 'a';
                    int b = s[j - l] - 'a';
                    freq[cnt[a]]--;
                    cnt[a]++;
                    freq[cnt[a]]++;

                    freq[cnt[b]]--;
                    cnt[b]--;
                    freq[cnt[b]]++;

                    if (freq[k] == i) {
                        ++ans;
                    }
                }
            }
            return ans;
        };
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && abs(word[j] - word[j - 1]) <= 2) {
                ++j;
            }
            ans += f(word.substr(i, j - i));
            i = j;
        }
        return ans;
    }
};
```

```go
func countCompleteSubstrings(word string, k int) (ans int) {
	n := len(word)
	f := func(s string) (ans int) {
		m := len(s)
		for i := 1; i <= 26 && i*k <= m; i++ {
			l := i * k
			cnt := [26]int{}
			for j := 0; j < l; j++ {
				cnt[int(s[j]-'a')]++
			}
			freq := map[int]int{}
			for _, x := range cnt {
				if x > 0 {
					freq[x]++
				}
			}
			if freq[k] == i {
				ans++
			}
			for j := l; j < m; j++ {
				a := int(s[j] - 'a')
				b := int(s[j-l] - 'a')
				freq[cnt[a]]--
				cnt[a]++
				freq[cnt[a]]++

				freq[cnt[b]]--
				cnt[b]--
				freq[cnt[b]]++

				if freq[k] == i {
					ans++
				}
			}
		}
		return
	}
	for i := 0; i < n; {
		j := i + 1
		for j < n && abs(int(word[j])-int(word[j-1])) <= 2 {
			j++
		}
		ans += f(word[i:j])
		i = j
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function countCompleteSubstrings(word: string, k: number): number {
    const f = (s: string): number => {
        const m = s.length;
        let ans = 0;
        for (let i = 1; i <= 26 && i * k <= m; i++) {
            const l = i * k;
            const cnt: number[] = new Array(26).fill(0);
            for (let j = 0; j < l; j++) {
                cnt[s.charCodeAt(j) - 'a'.charCodeAt(0)]++;
            }
            const freq: { [key: number]: number } = {};
            for (const x of cnt) {
                if (x > 0) {
                    freq[x] = (freq[x] || 0) + 1;
                }
            }
            if (freq[k] === i) {
                ans++;
            }

            for (let j = l; j < m; j++) {
                const a = s.charCodeAt(j) - 'a'.charCodeAt(0);
                const b = s.charCodeAt(j - l) - 'a'.charCodeAt(0);

                freq[cnt[a]]--;
                cnt[a]++;
                freq[cnt[a]] = (freq[cnt[a]] || 0) + 1;

                freq[cnt[b]]--;
                cnt[b]--;
                freq[cnt[b]] = (freq[cnt[b]] || 0) + 1;

                if (freq[k] === i) {
                    ans++;
                }
            }
        }

        return ans;
    };

    let n = word.length;
    let ans = 0;
    for (let i = 0; i < n; ) {
        let j = i + 1;
        while (j < n && Math.abs(word.charCodeAt(j) - word.charCodeAt(j - 1)) <= 2) {
            j++;
        }
        ans += f(word.substring(i, j));
        i = j;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
