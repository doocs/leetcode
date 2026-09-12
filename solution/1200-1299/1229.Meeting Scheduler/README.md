---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1229.Meeting%20Scheduler/README.md
rating: 1541
source: 第 11 场双周赛 Q2
tags:
    - 数组
    - 双指针
    - 排序
---

<!-- problem:start -->

# [1229. 安排会议日程 🔒](https://leetcode.cn/problems/meeting-scheduler)

[English Version](/solution/1200-1299/1229.Meeting%20Scheduler/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个人的空闲时间表：<code>slots1</code> 和 <code>slots2</code>，以及会议的预计持续时间&nbsp;<code>duration</code>，请你为他们安排&nbsp;<strong>时间段最早&nbsp;且</strong>合适的会议时间。</p>

<p>如果没有满足要求的会议时间，就请返回一个 <strong>空数组</strong>。</p>

<p>「空闲时间」的格式是&nbsp;<code>[start, end]</code>，由开始时间&nbsp;<code>start</code>&nbsp;和结束时间&nbsp;<code>end</code>&nbsp;组成，表示从&nbsp;<code>start</code>&nbsp;开始，到 <code>end</code>&nbsp;结束。&nbsp;</p>

<p>题目保证数据有效：同一个人的空闲时间不会出现交叠的情况，也就是说，对于同一个人的两个空闲时间&nbsp;<code>[start1, end1]</code>&nbsp;和&nbsp;<code>[start2, end2]</code>，要么&nbsp;<code>start1 &gt; end2</code>，要么&nbsp;<code>start2 &gt; end1</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
<strong>输出：</strong>[60,68]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= slots1.length, slots2.length &lt;= 10<sup>4</sup></code></li>
	<li><code>slots1[i].length, slots2[i].length == 2</code></li>
	<li><code>slots1[i][0] &lt; slots1[i][1]</code></li>
	<li><code>slots2[i][0] &lt; slots2[i][1]</code></li>
	<li><code>0 &lt;= slots1[i][j], slots2[i][j] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= duration &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 双指针

<!-- thinking:start -->

> **思考**
>
> 两人各有最多 $10^4$ 段空闲，两两求交为 $O(mn)$。各自按起点排序后，交集只可能发生在当前两段之间：交区间为起点取大、终点取小。
>
> 若长度达到 $duration$，取最早的一段即可。否则结束较早的一段不可能再与对方更靠后的段产生更早的可行交，对应指针前进。
>
> 排序保证候选按时间推进，双指针每次丢弃已无前途的一段，总比较次数线性。

<!-- thinking:end -->

我们可以将两个人的空闲时间分别排序，然后使用双指针遍历两个数组，找到两个人的空闲时间段的交集，如果交集的长度大于等于 `duration`，则返回交集的起始时间和起始时间加上 `duration`。否则，如果第一个人的空闲时间段的结束时间小于第二个人的空闲时间段的结束时间，我们就移动第一个人的指针，否则移动第二个人的指针。继续遍历，直到找到满足条件的时间段或者遍历结束。

时间复杂度 $O(m \times \log m + n \times \log n)$，空间复杂度 $O(\log m + \log n)$。其中 $m$ 和 $n$ 分别为两个数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minAvailableDuration(
        self, slots1: List[List[int]], slots2: List[List[int]], duration: int
    ) -> List[int]:
        slots1.sort()
        slots2.sort()
        m, n = len(slots1), len(slots2)
        i = j = 0
        while i < m and j < n:
            start = max(slots1[i][0], slots2[j][0])
            end = min(slots1[i][1], slots2[j][1])
            if end - start >= duration:
                return [start, start + duration]
            if slots1[i][1] < slots2[j][1]:
                i += 1
            else:
                j += 1
        return []
```

#### Java

```java
class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);
        int m = slots1.length, n = slots2.length;
        int i = 0, j = 0;
        while (i < m && j < n) {
            int start = Math.max(slots1[i][0], slots2[j][0]);
            int end = Math.min(slots1[i][1], slots2[j][1]);
            if (end - start >= duration) {
                return Arrays.asList(start, start + duration);
            }
            if (slots1[i][1] < slots2[j][1]) {
                ++i;
            } else {
                ++j;
            }
        }
        return Collections.emptyList();
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> minAvailableDuration(vector<vector<int>>& slots1, vector<vector<int>>& slots2, int duration) {
        sort(slots1.begin(), slots1.end());
        sort(slots2.begin(), slots2.end());
        int m = slots1.size(), n = slots2.size();
        int i = 0, j = 0;
        while (i < m && j < n) {
            int start = max(slots1[i][0], slots2[j][0]);
            int end = min(slots1[i][1], slots2[j][1]);
            if (end - start >= duration) {
                return {start, start + duration};
            }
            if (slots1[i][1] < slots2[j][1]) {
                ++i;
            } else {
                ++j;
            }
        }
        return {};
    }
};
```

#### Go

```go
func minAvailableDuration(slots1 [][]int, slots2 [][]int, duration int) []int {
	sort.Slice(slots1, func(i, j int) bool { return slots1[i][0] < slots1[j][0] })
	sort.Slice(slots2, func(i, j int) bool { return slots2[i][0] < slots2[j][0] })
	i, j, m, n := 0, 0, len(slots1), len(slots2)
	for i < m && j < n {
		start := max(slots1[i][0], slots2[j][0])
		end := min(slots1[i][1], slots2[j][1])
		if end-start >= duration {
			return []int{start, start + duration}
		}
		if slots1[i][1] < slots2[j][1] {
			i++
		} else {
			j++
		}
	}
	return []int{}
}
```

#### TypeScript

```ts
function minAvailableDuration(slots1: number[][], slots2: number[][], duration: number): number[] {
    slots1.sort((a, b) => a[0] - b[0]);
    slots2.sort((a, b) => a[0] - b[0]);
    const [m, n] = [slots1.length, slots2.length];
    let [i, j] = [0, 0];
    while (i < m && j < n) {
        const [start1, end1] = slots1[i];
        const [start2, end2] = slots2[j];
        const start = Math.max(start1, start2);
        const end = Math.min(end1, end2);
        if (end - start >= duration) {
            return [start, start + duration];
        }
        if (end1 < end2) {
            i++;
        } else {
            j++;
        }
    }
    return [];
}
```

#### Rust

```rust
impl Solution {
    pub fn min_available_duration(mut slots1: Vec<Vec<i32>>, mut slots2: Vec<Vec<i32>>, duration: i32) -> Vec<i32> {
        slots1.sort_by_key(|slot| slot[0]);
        slots2.sort_by_key(|slot| slot[0]);

        let (mut i, mut j) = (0, 0);
        let (m, n) = (slots1.len(), slots2.len());

        while i < m && j < n {
            let (start1, end1) = (slots1[i][0], slots1[i][1]);
            let (start2, end2) = (slots2[j][0], slots2[j][1]);

            let start = start1.max(start2);
            let end = end1.min(end2);

            if end - start >= duration {
                return vec![start, start + duration];
            }

            if end1 < end2 {
                i += 1;
            } else {
                j += 1;
            }
        }

        vec![]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
