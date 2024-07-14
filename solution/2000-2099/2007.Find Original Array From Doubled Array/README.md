---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2007.Find%20Original%20Array%20From%20Doubled%20Array/README.md
rating: 1557
source: 第 61 场双周赛 Q2
tags:
    - 贪心
    - 数组
    - 哈希表
    - 排序
---

<!-- problem:start -->

# [2007. 从双倍数组中还原原数组](https://leetcode.cn/problems/find-original-array-from-doubled-array)

[English Version](/solution/2000-2099/2007.Find%20Original%20Array%20From%20Doubled%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一个整数数组&nbsp;<code>original</code>&nbsp;可以转变成一个 <strong>双倍</strong>&nbsp;数组&nbsp;<code>changed</code>&nbsp;，转变方式为将 <code>original</code>&nbsp;中每个元素 <strong>值乘以 2 </strong>加入数组中，然后将所有元素 <strong>随机打乱</strong>&nbsp;。</p>

<p>给你一个数组&nbsp;<code>changed</code>&nbsp;，如果&nbsp;<code>change</code>&nbsp;是&nbsp;<strong>双倍</strong>&nbsp;数组，那么请你返回&nbsp;<code>original</code>数组，否则请返回空数组。<code>original</code>&nbsp;的元素可以以&nbsp;<strong>任意</strong>&nbsp;顺序返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>changed = [1,3,4,2,6,8]
<b>输出：</b>[1,3,4]
<b>解释：</b>一个可能的 original 数组为 [1,3,4] :
- 将 1 乘以 2 ，得到 1 * 2 = 2 。
- 将 3 乘以 2 ，得到 3 * 2 = 6 。
- 将 4 乘以 2 ，得到 4 * 2 = 8 。
其他可能的原数组方案为 [4,3,1] 或者 [3,1,4] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>changed = [6,3,0,1]
<b>输出：</b>[]
<b>解释：</b>changed 不是一个双倍数组。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>changed = [1]
<b>输出：</b>[]
<b>解释：</b>changed 不是一个双倍数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= changed.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= changed[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

我们注意到，如果数组 `changed` 是一个双倍数组，那么数组 `changed` 中最小的元素也一定是原数组中的元素，因此，我们可以先对数组 `changed` 进行排序，然后以第一个元素作为起点，从小到大遍历数组 `changed`。

我们使用一个哈希表或数组 $cnt$ 来统计数组 `changed` 中每个元素的出现次数。对于数组 `changed` 中的每个元素 $x$，我们首先检查 $x$ 是否存在于 $cnt$ 中。如果不存在，我们直接跳过这个元素。否则，我们将 $cnt[x]$ 减一，并检查 $x \times 2$ 是否存在于 $cnt$ 中。如果不存在，我们直接返回一个空数组。否则，我们将 $cnt[x \times 2]$ 减一，并将 $x$ 加入答案数组中。

遍历结束后，返回答案数组即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$，其中 $n$ 为数组 `changed` 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findOriginalArray(self, changed: List[int]) -> List[int]:
        changed.sort()
        cnt = Counter(changed)
        ans = []
        for x in changed:
            if cnt[x] == 0:
                continue
            cnt[x] -= 1
            if cnt[x << 1] <= 0:
                return []
            cnt[x << 1] -= 1
            ans.append(x)
        return ans
```

#### Java

```java
class Solution {
    public int[] findOriginalArray(int[] changed) {
        int n = changed.length;
        Arrays.sort(changed);
        int[] cnt = new int[changed[n - 1] + 1];
        for (int x : changed) {
            ++cnt[x];
        }
        int[] ans = new int[n >> 1];
        int i = 0;
        for (int x : changed) {
            if (cnt[x] == 0) {
                continue;
            }
            --cnt[x];
            int y = x << 1;
            if (y >= cnt.length || cnt[y] <= 0) {
                return new int[0];
            }
            --cnt[y];
            ans[i++] = x;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> findOriginalArray(vector<int>& changed) {
        sort(changed.begin(), changed.end());
        vector<int> cnt(changed.back() + 1);
        for (int x : changed) {
            ++cnt[x];
        }
        vector<int> ans;
        for (int x : changed) {
            if (cnt[x] == 0) {
                continue;
            }
            --cnt[x];
            int y = x << 1;
            if (y >= cnt.size() || cnt[y] <= 0) {
                return {};
            }
            --cnt[y];
            ans.push_back(x);
        }
        return ans;
    }
};
```

#### Go

```go
func findOriginalArray(changed []int) (ans []int) {
	sort.Ints(changed)
	cnt := make([]int, changed[len(changed)-1]+1)
	for _, x := range changed {
		cnt[x]++
	}
	for _, x := range changed {
		if cnt[x] == 0 {
			continue
		}
		cnt[x]--
		y := x << 1
		if y >= len(cnt) || cnt[y] <= 0 {
			return []int{}
		}
		cnt[y]--
		ans = append(ans, x)
	}
	return
}
```

#### TypeScript

```ts
function findOriginalArray(changed: number[]): number[] {
    changed.sort((a, b) => a - b);
    const cnt: number[] = Array(changed.at(-1)! + 1).fill(0);
    for (const x of changed) {
        ++cnt[x];
    }
    const ans: number[] = [];
    for (const x of changed) {
        if (cnt[x] === 0) {
            continue;
        }
        cnt[x]--;
        const y = x << 1;
        if (y >= cnt.length || cnt[y] <= 0) {
            return [];
        }
        cnt[y]--;
        ans.push(x);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
