---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0825.Friends%20Of%20Appropriate%20Ages/README.md
tags:
    - 数组
    - 双指针
    - 二分查找
    - 排序
---

<!-- problem:start -->

# [825. 适龄的朋友](https://leetcode.cn/problems/friends-of-appropriate-ages)

[English Version](/solution/0800-0899/0825.Friends%20Of%20Appropriate%20Ages/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在社交媒体网站上有 <code>n</code> 个用户。给你一个整数数组 <code>ages</code> ，其中 <code>ages[i]</code> 是第 <code>i</code> 个用户的年龄。</p>

<p>如果下述任意一个条件为真，那么用户 <code>x</code> 将不会向用户 <code>y</code>（<code>x != y</code>）发送好友请求：</p>

<ul>
	<li><code>ages[y] &lt;= 0.5 * ages[x] + 7</code></li>
	<li><code>ages[y] &gt; ages[x]</code></li>
	<li><code>ages[y] &gt; 100 &amp;&amp; ages[x] &lt; 100</code></li>
</ul>

<p>否则，<code>x</code> 将会向 <code>y</code> 发送一条好友请求。</p>

<p>注意，如果 <code>x</code> 向 <code>y</code> 发送一条好友请求，<code>y</code> 不必也向 <code>x</code> 发送一条好友请求。另外，用户不会向自己发送好友请求。</p>

<p>返回在该社交媒体网站上产生的好友请求总数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>ages = [16,16]
<strong>输出：</strong>2
<strong>解释：</strong>2 人互发好友请求。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>ages = [16,17,18]
<strong>输出：</strong>2
<strong>解释：</strong>产生的好友请求为 17 -&gt; 16 ，18 -&gt; 17 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>ages = [20,30,100,110,120]
<strong>输出：</strong>3
<strong>解释：</strong>产生的好友请求为 110 -&gt; 100 ，120 -&gt; 110 ，120 -&gt; 100 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == ages.length</code></li>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= ages[i] &lt;= 120</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数 + 枚举

我们可以用一个长度为 $121$ 的数组 $\textit{cnt}$ 记录每个年龄的人数。

接下来，枚举所有可能的年龄对 $(\textit{ax}, \textit{ay})$，如果 $\textit{ax}$ 和 $\textit{ay}$ 不满足题目中的任意一个条件，这些年龄对 $(\textit{ax}, \textit{ay})$ 就可以互发好友请求。

此时，如果 $\textit{ax} = \textit{ay}$，年龄相同，那么 $\textit{ax}$ 和 $\textit{ay}$ 之间的好友请求数为 $\textit{cnt}[\textit{ax}] \times (\textit{cnt}[\textit{ax}] - 1)$；否则，年龄不同，那么 $\textit{ax}$ 和 $\textit{ay}$ 之间的好友请求数为 $\textit{cnt}[\textit{ax}] \times \textit{cnt}[\textit{ay}]$。我们将这些好友请求数累加到答案中即可。

时间复杂度 $O(n + m^2)$，其中 $n$ 是数组 $\textit{ages}$ 的长度，而 $m$ 是年龄的最大值，本题中 $m = 121$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numFriendRequests(self, ages: List[int]) -> int:
        cnt = [0] * 121
        for x in ages:
            cnt[x] += 1
        ans = 0
        for ax, x in enumerate(cnt):
            for ay, y in enumerate(cnt):
                if not (ay <= 0.5 * ax + 7 or ay > ax or (ay > 100 and ax < 100)):
                    ans += x * (y - int(ax == ay))
        return ans
```

#### Java

```java
class Solution {
    public int numFriendRequests(int[] ages) {
        final int m = 121;
        int[] cnt = new int[m];
        for (int x : ages) {
            ++cnt[x];
        }
        int ans = 0;
        for (int ax = 1; ax < m; ++ax) {
            for (int ay = 1; ay < m; ++ay) {
                if (!(ay <= 0.5 * ax + 7 || ay > ax || (ay > 100 && ax < 100))) {
                    ans += cnt[ax] * (cnt[ay] - (ax == ay ? 1 : 0));
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numFriendRequests(vector<int>& ages) {
        const int m = 121;
        vector<int> cnt(m);
        for (int x : ages) {
            ++cnt[x];
        }
        int ans = 0;
        for (int ax = 1; ax < m; ++ax) {
            for (int ay = 1; ay < m; ++ay) {
                if (!(ay <= 0.5 * ax + 7 || ay > ax || (ay > 100 && ax < 100))) {
                    ans += cnt[ax] * (cnt[ay] - (ax == ay ? 1 : 0));
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func numFriendRequests(ages []int) (ans int) {
	cnt := [121]int{}
	for _, x := range ages {
		cnt[x]++
	}
	for ax, x := range cnt {
		for ay, y := range cnt {
			if ay <= ax/2+7 || ay > ax || (ay > 100 && ax < 100) {
				continue
			}
			if ax == ay {
				ans += x * (x - 1)
			} else {
				ans += x * y
			}
		}
	}

	return
}
```

#### TypeScript

```ts
function numFriendRequests(ages: number[]): number {
    const m = 121;
    const cnt = Array(m).fill(0);
    for (const x of ages) {
        cnt[x]++;
    }

    let ans = 0;
    for (let ax = 0; ax < m; ax++) {
        for (let ay = 0; ay < m; ay++) {
            if (ay <= 0.5 * ax + 7 || ay > ax || (ay > 100 && ax < 100)) {
                continue;
            }
            ans += cnt[ax] * (cnt[ay] - (ax === ay ? 1 : 0));
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
