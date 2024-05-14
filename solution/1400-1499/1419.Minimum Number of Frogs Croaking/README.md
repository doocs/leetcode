---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1419.Minimum%20Number%20of%20Frogs%20Croaking/README.md
rating: 1689
tags:
    - 字符串
    - 计数
---

# [1419. 数青蛙](https://leetcode.cn/problems/minimum-number-of-frogs-croaking)

[English Version](/solution/1400-1499/1419.Minimum%20Number%20of%20Frogs%20Croaking/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>croakOfFrogs</code>，它表示不同青蛙发出的蛙鸣声（字符串 <code>"croak"</code> ）的组合。由于同一时间可以有多只青蛙呱呱作响，所以&nbsp;<code>croakOfFrogs</code> 中会混合多个 <code>“croak”</code> <em>。</em></p>

<p>请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。</p>

<p>要想发出蛙鸣 "croak"，青蛙必须 <strong>依序</strong> 输出 <code>‘c’, ’r’, ’o’, ’a’, ’k’</code> 这 5 个字母。如果没有输出全部五个字母，那么它就不会发出声音。如果字符串 <code>croakOfFrogs</code> 不是由若干有效的 "croak" 字符混合而成，请返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>croakOfFrogs = "croakcroak"
<strong>输出：</strong>1 
<strong>解释：</strong>一只青蛙 “呱呱” 两次
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>croakOfFrogs = "crcoakroak"
<strong>输出：</strong>2 
<strong>解释：</strong>最少需要两只青蛙，“呱呱” 声用黑体标注
第一只青蛙 "<strong>cr</strong>c<strong>oak</strong>roak"
第二只青蛙 "cr<strong>c</strong>oak<strong>roak</strong>"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>croakOfFrogs = "croakcrook"
<strong>输出：</strong>-1
<strong>解释：</strong>给出的字符串不是 "croak<strong>"</strong> 的有效组合。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= croakOfFrogs.length &lt;= 10<sup>5</sup></code></li>
	<li>字符串中的字符只有 <code>'c'</code>, <code>'r'</code>, <code>'o'</code>, <code>'a'</code> 或者 <code>'k'</code></li>
</ul>

## 解法

### 方法一：计数 + 模拟

我们注意到，如果字符串 `croakOfFrogs` 是由若干有效的 `"croak"` 字符混合而成，那么它的长度一定是 $5$ 的倍数。因此，如果字符串的长度不是 $5$ 的倍数，可以直接返回 $-1$。

接下来，我们将 `'c'`, `'r'`, `'o'`, `'a'`, `'k'` 这 $5$ 个字母分别对应下标 $0$ 到 $4$，用一个长度为 $5$ 的数组 $cnt$ 记录字符串 `croakOfFrogs` 中每个字母出现的次数，其中 $cnt[i]$ 表示当前下标为 $i$ 的字母出现的次数。另外，我们定义一个整数变量 $x$ 表示当前未完成蛙鸣的青蛙的数目，需要的青蛙的最少数目 $ans$ 即为 $x$ 的最大值。

我们遍历字符串 `croakOfFrogs` 中的每个字母 $c$，找到 $c$ 对应的下标 $i$，然后将 $cnt[i]$ 加 $1$。接下来，根据 $i$ 值的不同，我们分别进行如下操作：

-   如果 $i=0$，那么当前有一个新的青蛙开始蛙鸣，因此令 $x$ 的值加 $1$，然后我们更新 $ans = \max(ans, x)$；
-   否则，如果 $cnt[i-1]=0$，那么表示当前没有青蛙可以发出字母 $c$，无法完成蛙鸣，返回 $-1$，否则我们令 $cnt[i-1]$ 减 $1$。如果 $i=4$，那么表示青蛙已经完成了一个蛙鸣，因此令 $x$ 的值减 $1$。

遍历结束后，如果 $x=0$，那么说明青蛙已经完成了所有的蛙鸣，返回 $ans$，否则返回 $-1$。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 是字符串 `croakOfFrogs` 的长度；而 $C$ 是字符集的大小，本题中 $C=26$。

<!-- tabs:start -->

```python
class Solution:
    def minNumberOfFrogs(self, croakOfFrogs: str) -> int:
        if len(croakOfFrogs) % 5 != 0:
            return -1
        idx = {c: i for i, c in enumerate('croak')}
        cnt = [0] * 5
        ans = x = 0
        for i in map(idx.get, croakOfFrogs):
            cnt[i] += 1
            if i == 0:
                x += 1
                ans = max(ans, x)
            else:
                if cnt[i - 1] == 0:
                    return -1
                cnt[i - 1] -= 1
                if i == 4:
                    x -= 1
        return -1 if x else ans
```

```java
class Solution {
    public int minNumberOfFrogs(String croakOfFrogs) {
        int n = croakOfFrogs.length();
        if (n % 5 != 0) {
            return -1;
        }
        int[] idx = new int[26];
        String s = "croak";
        for (int i = 0; i < 5; ++i) {
            idx[s.charAt(i) - 'a'] = i;
        }
        int[] cnt = new int[5];
        int ans = 0, x = 0;
        for (int k = 0; k < n; ++k) {
            int i = idx[croakOfFrogs.charAt(k) - 'a'];
            ++cnt[i];
            if (i == 0) {
                ans = Math.max(ans, ++x);
            } else {
                if (--cnt[i - 1] < 0) {
                    return -1;
                }
                if (i == 4) {
                    --x;
                }
            }
        }
        return x > 0 ? -1 : ans;
    }
}
```

```cpp
class Solution {
public:
    int minNumberOfFrogs(string croakOfFrogs) {
        int n = croakOfFrogs.size();
        if (n % 5 != 0) {
            return -1;
        }
        int idx[26]{};
        string s = "croak";
        for (int i = 0; i < 5; ++i) {
            idx[s[i] - 'a'] = i;
        }
        int cnt[5]{};
        int ans = 0, x = 0;
        for (char& c : croakOfFrogs) {
            int i = idx[c - 'a'];
            ++cnt[i];
            if (i == 0) {
                ans = max(ans, ++x);
            } else {
                if (--cnt[i - 1] < 0) {
                    return -1;
                }
                if (i == 4) {
                    --x;
                }
            }
        }
        return x > 0 ? -1 : ans;
    }
};
```

```go
func minNumberOfFrogs(croakOfFrogs string) int {
	n := len(croakOfFrogs)
	if n%5 != 0 {
		return -1
	}
	idx := [26]int{}
	for i, c := range "croak" {
		idx[c-'a'] = i
	}
	cnt := [5]int{}
	ans, x := 0, 0
	for _, c := range croakOfFrogs {
		i := idx[c-'a']
		cnt[i]++
		if i == 0 {
			x++
			ans = max(ans, x)
		} else {
			cnt[i-1]--
			if cnt[i-1] < 0 {
				return -1
			}
			if i == 4 {
				x--
			}
		}
	}
	if x > 0 {
		return -1
	}
	return ans
}
```

```ts
function minNumberOfFrogs(croakOfFrogs: string): number {
    const n = croakOfFrogs.length;
    if (n % 5 !== 0) {
        return -1;
    }
    const idx = (c: string): number => 'croak'.indexOf(c);
    const cnt: number[] = [0, 0, 0, 0, 0];
    let ans = 0;
    let x = 0;
    for (const c of croakOfFrogs) {
        const i = idx(c);
        ++cnt[i];
        if (i === 0) {
            ans = Math.max(ans, ++x);
        } else {
            if (--cnt[i - 1] < 0) {
                return -1;
            }
            if (i === 4) {
                --x;
            }
        }
    }
    return x > 0 ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- end -->
