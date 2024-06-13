---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3175.Find%20The%20First%20Player%20to%20win%20K%20Games%20in%20a%20Row/README.md
tags:
    - 数组
    - 模拟
---

<!-- problem:start -->

# [3175. 找到连续赢 K 场比赛的第一位玩家](https://leetcode.cn/problems/find-the-first-player-to-win-k-games-in-a-row)

[English Version](/solution/3100-3199/3175.Find%20The%20First%20Player%20to%20win%20K%20Games%20in%20a%20Row/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有&nbsp;<code>n</code>&nbsp;位玩家在进行比赛，玩家编号依次为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;。</p>

<p>给你一个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>skills</code>&nbsp;和一个 <strong>正</strong>&nbsp;整数&nbsp;<code>k</code>&nbsp;，其中&nbsp;<code>skills[i]</code>&nbsp;是第 <code>i</code>&nbsp;位玩家的技能等级。<code>skills</code>&nbsp;中所有整数 <strong>互不相同</strong>&nbsp;。</p>

<p>所有玩家从编号 <code>0</code>&nbsp;到 <code>n - 1</code>&nbsp;排成一列。</p>

<p>比赛进行方式如下：</p>

<ul>
	<li>队列中最前面两名玩家进行一场比赛，技能等级 <strong>更高</strong>&nbsp;的玩家胜出。</li>
	<li>比赛后，获胜者保持在队列的开头，而失败者排到队列的末尾。</li>
</ul>

<p>这个比赛的赢家是 <strong>第一位连续</strong>&nbsp;赢下&nbsp;<code>k</code>&nbsp;场比赛的玩家。</p>

<p>请你返回这个比赛的赢家编号。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>skills = [4,2,6,3,9], k = 2</span></p>

<p><b>输出：</b>2</p>

<p><strong>解释：</strong></p>

<p>一开始，队列里的玩家为&nbsp;<code>[0,1,2,3,4]</code>&nbsp;。比赛过程如下：</p>

<ul>
	<li>玩家 0 和 1 进行一场比赛，玩家 0 的技能等级高于玩家 1 ，玩家 0 胜出，队列变为&nbsp;<code>[0,2,3,4,1]</code>&nbsp;。</li>
	<li>玩家 0 和 2 进行一场比赛，玩家 2 的技能等级高于玩家 0 ，玩家 2 胜出，队列变为&nbsp;<code>[2,3,4,1,0]</code>&nbsp;。</li>
	<li>玩家 2 和 3 进行一场比赛，玩家 2 的技能等级高于玩家 3 ，玩家 2 胜出，队列变为&nbsp;<code>[2,4,1,0,3]</code>&nbsp;。</li>
</ul>

<p>玩家 2 连续赢了&nbsp;<code>k = 2</code>&nbsp;场比赛，所以赢家是玩家 2 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>skills = [2,5,4], k = 3</span></p>

<p><b>输出：</b>1</p>

<p><strong>解释：</strong></p>

<p>一开始，队列里的玩家为&nbsp;<code>[0,1,2]</code>&nbsp;。比赛过程如下：</p>

<ul>
	<li>玩家 0 和 1 进行一场比赛，玩家 1 的技能等级高于玩家 0 ，玩家 1 胜出，队列变为&nbsp;<code>[1,2,0]</code>&nbsp;。</li>
	<li>玩家 1&nbsp;和 2&nbsp;进行一场比赛，玩家 1 的技能等级高于玩家 2&nbsp;，玩家 1 胜出，队列变为&nbsp;<code>[1,0,2]</code>&nbsp;。</li>
	<li>玩家 1&nbsp;和 0&nbsp;进行一场比赛，玩家 1 的技能等级高于玩家 0&nbsp;，玩家 1 胜出，队列变为&nbsp;<code>[1,2,0]</code>&nbsp;。</li>
</ul>

<p>玩家 1 连续赢了&nbsp;<code>k = 3</code>&nbsp;场比赛，所以赢家是玩家 1 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == skills.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= skills[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>skills</code>&nbsp;中的整数互不相同。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：脑筋急转弯

我们注意到，每次会比较数组的前两个元素，不管结果怎么样，下一次的比较，一定是轮到了数组中的下一个元素和当前的胜者进行比较。因此，如果循环了 $n-1$ 次，那么最后的胜者一定是数组中的最大元素。否则，如果某个元素连续胜出了 $k$ 次，那么这个元素就是最后的胜者。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

相似题目：

-   [1535. 找到数组中的赢家](https://github.com/doocs/leetcode/blob/main/solution/1500-1599/1535.Find%20the%20Winner%20of%20an%20Array%20Game/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findWinningPlayer(self, skills: List[int], k: int) -> int:
        n = len(skills)
        k = min(k, n - 1)
        i = cnt = 0
        for j in range(1, n):
            if skills[i] < skills[j]:
                i = j
                cnt = 1
            else:
                cnt += 1
            if cnt == k:
                break
        return i
```

#### Java

```java
class Solution {
    public int findWinningPlayer(int[] skills, int k) {
        int n = skills.length;
        k = Math.min(k, n - 1);
        int i = 0, cnt = 0;
        for (int j = 1; j < n; ++j) {
            if (skills[i] < skills[j]) {
                i = j;
                cnt = 1;
            } else {
                ++cnt;
            }
            if (cnt == k) {
                break;
            }
        }
        return i;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findWinningPlayer(vector<int>& skills, int k) {
        int n = skills.size();
        k = min(k, n - 1);
        int i = 0, cnt = 0;
        for (int j = 1; j < n; ++j) {
            if (skills[i] < skills[j]) {
                i = j;
                cnt = 1;
            } else {
                ++cnt;
            }
            if (cnt == k) {
                break;
            }
        }
        return i;
    }
};
```

#### Go

```go
func findWinningPlayer(skills []int, k int) int {
	n := len(skills)
	k = min(k, n-1)
	i, cnt := 0, 0
	for j := 1; j < n; j++ {
		if skills[i] < skills[j] {
			i = j
			cnt = 1
		} else {
			cnt++
		}
		if cnt == k {
			break
		}
	}
	return i
}
```

#### TypeScript

```ts
function findWinningPlayer(skills: number[], k: number): number {
    const n = skills.length;
    k = Math.min(k, n - 1);
    let [i, cnt] = [0, 0];
    for (let j = 1; j < n; ++j) {
        if (skills[i] < skills[j]) {
            i = j;
            cnt = 1;
        } else {
            ++cnt;
        }
        if (cnt === k) {
            break;
        }
    }
    return i;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
