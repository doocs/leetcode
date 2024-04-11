# [1100. 长度为 K 的无重复字符子串](https://leetcode.cn/problems/find-k-length-substrings-with-no-repeated-characters)

[English Version](/solution/1100-1199/1100.Find%20K-Length%20Substrings%20With%20No%20Repeated%20Characters/README_EN.md)

<!-- tags:哈希表,字符串,滑动窗口 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>S</code>，找出所有长度为&nbsp;<code>K</code>&nbsp;且不含重复字符的子串，请你返回全部满足要求的子串的&nbsp;<strong>数目</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>S = &quot;havefunonleetcode&quot;, K = 5
<strong>输出：</strong>6
<strong>解释：</strong>
这里有 6 个满足题意的子串，分别是：&#39;havef&#39;,&#39;avefu&#39;,&#39;vefun&#39;,&#39;efuno&#39;,&#39;etcod&#39;,&#39;tcode&#39;。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>S = &quot;home&quot;, K = 5
<strong>输出：</strong>0
<strong>解释：</strong>
注意：K 可能会大于 S 的长度。在这种情况下，就无法找到任何长度为 K 的子串。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= S.length &lt;= 10^4</code></li>
	<li><code>S</code> 中的所有字符均为小写英文字母</li>
	<li><code>1 &lt;= K &lt;= 10^4</code></li>
</ol>

## 解法

### 方法一：滑动窗口 + 哈希表

我们维护一个长度为 $k$ 的滑动窗口，用一个哈希表 $cnt$ 统计窗口中每个字符的出现次数。

首先，我们将字符串 $s$ 的前 $k$ 个字符加入哈希表 $cnt$ 中，并判断 $cnt$ 的大小是否等于 $k$，如果等于 $k$，则说明窗口中的字符都不相同，答案 $ans$ 加一。

接下来，我们从 $k$ 开始遍历字符串 $s$，每次将 $s[i]$ 加入哈希表 $cnt$ 中，同时将 $s[i-k]$ 从哈希表 $cnt$ 中减一，如果 $cnt[s[i-k]]$ 减一后等于 $0$，则将 $s[i-k]$ 从哈希表 $cnt$ 中删除。如果此时哈希表 $cnt$ 的大小等于 $k$，则说明窗口中的字符都不相同，答案 $ans$ 加一。

最后，返回答案 $ans$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(\min(k, |\Sigma|))$，其中 $n$ 为字符串 $s$ 的长度；而 $\Sigma$ 为字符集，本题中字符集为小写英文字母，所以 $|\Sigma| = 26$。

<!-- tabs:start -->

```python
class Solution:
    def numKLenSubstrNoRepeats(self, s: str, k: int) -> int:
        cnt = Counter(s[:k])
        ans = int(len(cnt) == k)
        for i in range(k, len(s)):
            cnt[s[i]] += 1
            cnt[s[i - k]] -= 1
            if cnt[s[i - k]] == 0:
                cnt.pop(s[i - k])
            ans += int(len(cnt) == k)
        return ans
```

```java
class Solution {
    public int numKLenSubstrNoRepeats(String s, int k) {
        int n = s.length();
        if (n < k) {
            return 0;
        }
        Map<Character, Integer> cnt = new HashMap<>(k);
        for (int i = 0; i < k; ++i) {
            cnt.merge(s.charAt(i), 1, Integer::sum);
        }
        int ans = cnt.size() == k ? 1 : 0;
        for (int i = k; i < n; ++i) {
            cnt.merge(s.charAt(i), 1, Integer::sum);
            if (cnt.merge(s.charAt(i - k), -1, Integer::sum) == 0) {
                cnt.remove(s.charAt(i - k));
            }
            ans += cnt.size() == k ? 1 : 0;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numKLenSubstrNoRepeats(string s, int k) {
        int n = s.size();
        if (n < k) {
            return 0;
        }
        unordered_map<char, int> cnt;
        for (int i = 0; i < k; ++i) {
            ++cnt[s[i]];
        }
        int ans = cnt.size() == k;
        for (int i = k; i < n; ++i) {
            ++cnt[s[i]];
            if (--cnt[s[i - k]] == 0) {
                cnt.erase(s[i - k]);
            }
            ans += cnt.size() == k;
        }
        return ans;
    }
};
```

```go
func numKLenSubstrNoRepeats(s string, k int) (ans int) {
	n := len(s)
	if n < k {
		return
	}
	cnt := map[byte]int{}
	for i := 0; i < k; i++ {
		cnt[s[i]]++
	}
	if len(cnt) == k {
		ans++
	}
	for i := k; i < n; i++ {
		cnt[s[i]]++
		cnt[s[i-k]]--
		if cnt[s[i-k]] == 0 {
			delete(cnt, s[i-k])
		}
		if len(cnt) == k {
			ans++
		}
	}
	return
}
```

```ts
function numKLenSubstrNoRepeats(s: string, k: number): number {
    const n = s.length;
    if (n < k) {
        return 0;
    }
    const cnt: Map<string, number> = new Map();
    for (let i = 0; i < k; ++i) {
        cnt.set(s[i], (cnt.get(s[i]) ?? 0) + 1);
    }
    let ans = cnt.size === k ? 1 : 0;
    for (let i = k; i < n; ++i) {
        cnt.set(s[i], (cnt.get(s[i]) ?? 0) + 1);
        cnt.set(s[i - k], (cnt.get(s[i - k]) ?? 0) - 1);
        if (cnt.get(s[i - k]) === 0) {
            cnt.delete(s[i - k]);
        }
        ans += cnt.size === k ? 1 : 0;
    }
    return ans;
}
```

```php
class Solution {
    /**
     * @param String $s
     * @param Integer $k
     * @return Integer
     */
    function numKLenSubstrNoRepeats($s, $k) {
        $n = strlen($s);
        if ($n < $k) {
            return 0;
        }
        $cnt = [];
        for ($i = 0; $i < $k; ++$i) {
            if (!isset($cnt[$s[$i]])) {
                $cnt[$s[$i]] = 1;
            } else {
                $cnt[$s[$i]]++;
            }
        }
        $ans = count($cnt) == $k ? 1 : 0;
        for ($i = $k; $i < $n; ++$i) {
            if (!isset($cnt[$s[$i]])) {
                $cnt[$s[$i]] = 1;
            } else {
                $cnt[$s[$i]]++;
            }
            if ($cnt[$s[$i - $k]] - 1 == 0) {
                unset($cnt[$s[$i - $k]]);
            } else {
                $cnt[$s[$i - $k]]--;
            }
            $ans += count($cnt) == $k ? 1 : 0;
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- end -->
