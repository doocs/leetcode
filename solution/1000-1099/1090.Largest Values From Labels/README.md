---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1090.Largest%20Values%20From%20Labels/README.md
rating: 1501
source: 第 141 场周赛 Q2
tags:
    - 贪心
    - 数组
    - 哈希表
    - 计数
    - 排序
---

<!-- problem:start -->

# [1090. 受标签影响的最大值](https://leetcode.cn/problems/largest-values-from-labels)

[English Version](/solution/1000-1099/1090.Largest%20Values%20From%20Labels/README_EN.md)

## 题目描述

<!-- description:start -->

<p>以两个整数数组 &nbsp;<code>values</code>&nbsp;和 <code>labels</code>&nbsp;给定&nbsp;<code>n</code>&nbsp;个项的值和标签，并且给出两个整数&nbsp;<code>numWanted</code>&nbsp;和 <code>useLimit</code> 。</p>

<p>你的任务是从这些项中找到一个值的和 <strong>最大</strong> 的子集使得：</p>

<ul>
	<li>项的数量 <strong>最多</strong> 为&nbsp;<code>numWanted</code>。</li>
	<li>相同标签的项的数量&nbsp;<strong>最多 </strong>为&nbsp;<code>useLimit</code>。</li>
</ul>

<p>返回最大的和。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">values = [5,4,3,2,1], labels = [1,1,2,2,3], numWanted = 3, useLimit = 1</span></p>

<p><strong>输出：</strong><span class="example-io">9</span></p>

<p><strong>解释：</strong></p>

<p>选择的子集是第一个、第三个和第五个项，其值之和为 5 + 3 + 1。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">values = [5,4,3,2,1], labels = [1,3,3,3,2], numWanted = 3, useLimit = 2</span></p>

<p><strong>输出：</strong><span class="example-io">12</span></p>

<p><strong>解释：</strong></p>

<p>选择的子集是第一个、第二个和第三个项，其值之和为 5 + 4 + 3。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">values = [9,8,8,7,6], labels = [0,0,0,1,1], numWanted = 3, useLimit = 1</span></p>

<p><strong>输出：</strong><span class="example-io">16</span></p>

<p><strong>解释：</strong></p>

<p>选择的子集是第一个和第四个项，其值之和为 9 + 7。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == values.length == labels.length</code></li>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= values[i], labels[i] &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= numWanted, useLimit &lt;= n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 排序 + 哈希表

根据题目描述，我们需要从 $n$ 个元素的集合中选出一个子集，子集元素个数不超过 $numWanted$，且子集中最多有相同标签的 $useLimit$ 项，使得子集的值之和最大。因此，我们应该贪心地选择集合中值较大的元素，同时记录每个标签出现的次数，当某个标签出现的次数达到 $useLimit$ 时，我们就不能再选择该标签对应的元素了。

具体地，我们先将集合中的元素按照值从大到小进行排序，然后从前向后遍历排序后的元素。在遍历的过程中，我们使用一个哈希表 $cnt$ 记录每个标签出现的次数，如果某个标签出现的次数达到了 $useLimit$，那么我们就跳过该元素，否则我们就将该元素的值加到最终的答案中，并将该标签出现的次数加 $1$。同时，我们用一个变量 $num$ 记录当前子集中的元素个数，当 $num$ 达到 $numWanted$ 时，我们就可以结束遍历了。

遍历结束后，我们就得到了最大的分数。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是集合中的元素个数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestValsFromLabels(
        self, values: List[int], labels: List[int], numWanted: int, useLimit: int
    ) -> int:
        ans = num = 0
        cnt = Counter()
        for v, l in sorted(zip(values, labels), reverse=True):
            if cnt[l] < useLimit:
                cnt[l] += 1
                num += 1
                ans += v
                if num == numWanted:
                    break
        return ans
```

#### Java

```java
class Solution {
    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int n = values.length;
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; ++i) {
            pairs[i] = new int[] {values[i], labels[i]};
        }
        Arrays.sort(pairs, (a, b) -> b[0] - a[0]);
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0, num = 0;
        for (int i = 0; i < n && num < numWanted; ++i) {
            int v = pairs[i][0], l = pairs[i][1];
            if (cnt.getOrDefault(l, 0) < useLimit) {
                cnt.merge(l, 1, Integer::sum);
                num += 1;
                ans += v;
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
    int largestValsFromLabels(vector<int>& values, vector<int>& labels, int numWanted, int useLimit) {
        int n = values.size();
        vector<pair<int, int>> pairs(n);
        for (int i = 0; i < n; ++i) {
            pairs[i] = {-values[i], labels[i]};
        }
        sort(pairs.begin(), pairs.end());
        unordered_map<int, int> cnt;
        int ans = 0, num = 0;
        for (int i = 0; i < n && num < numWanted; ++i) {
            int v = -pairs[i].first, l = pairs[i].second;
            if (cnt[l] < useLimit) {
                ++cnt[l];
                ++num;
                ans += v;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func largestValsFromLabels(values []int, labels []int, numWanted int, useLimit int) (ans int) {
	n := len(values)
	pairs := make([][2]int, n)
	for i := 0; i < n; i++ {
		pairs[i] = [2]int{values[i], labels[i]}
	}
	sort.Slice(pairs, func(i, j int) bool { return pairs[i][0] > pairs[j][0] })
	cnt := map[int]int{}
	for i, num := 0, 0; i < n && num < numWanted; i++ {
		v, l := pairs[i][0], pairs[i][1]
		if cnt[l] < useLimit {
			cnt[l]++
			num++
			ans += v
		}
	}
	return
}
```

#### TypeScript

```ts
function largestValsFromLabels(
    values: number[],
    labels: number[],
    numWanted: number,
    useLimit: number,
): number {
    const n = values.length;
    const pairs = new Array(n);
    for (let i = 0; i < n; ++i) {
        pairs[i] = [values[i], labels[i]];
    }
    pairs.sort((a, b) => b[0] - a[0]);
    const cnt: Map<number, number> = new Map();
    let ans = 0;
    for (let i = 0, num = 0; i < n && num < numWanted; ++i) {
        const [v, l] = pairs[i];
        if ((cnt.get(l) || 0) < useLimit) {
            cnt.set(l, (cnt.get(l) || 0) + 1);
            ++num;
            ans += v;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
