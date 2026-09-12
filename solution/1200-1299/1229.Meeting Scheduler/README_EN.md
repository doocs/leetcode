---
comments: true
difficulty: Medium
rating: 1541
source: Biweekly Contest 11 Q2
tags:
    - Array
    - Two Pointers
    - Sorting
---

<!-- problem:start -->

# [1229. Meeting Scheduler 🔒](https://leetcode.com/problems/meeting-scheduler)

[中文文档](/solution/1200-1299/1229.Meeting%20Scheduler/README.md)

## Description

<!-- description:start -->

<p>Given the availability time slots arrays <code>slots1</code> and <code>slots2</code> of two people and a meeting duration <code>duration</code>, return the <strong>earliest time slot</strong> that works for both of them and is of duration <code>duration</code>.</p>

<p>If there is no common time slot that satisfies the requirements, return an <strong>empty array</strong>.</p>

<p>The format of a time slot is an array of two elements <code>[start, end]</code> representing an inclusive time range from <code>start</code> to <code>end</code>.</p>

<p>It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots <code>[start1, end1]</code> and <code>[start2, end2]</code> of the same person, either <code>start1 &gt; end2</code> or <code>start2 &gt; end1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
<strong>Output:</strong> [60,68]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= slots1.length, slots2.length &lt;= 10<sup>4</sup></code></li>
	<li><code>slots1[i].length, slots2[i].length == 2</code></li>
	<li><code>slots1[i][0] &lt; slots1[i][1]</code></li>
	<li><code>slots2[i][0] &lt; slots2[i][1]</code></li>
	<li><code>0 &lt;= slots1[i][j], slots2[i][j] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= duration &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Two Pointers

<!-- thinking:start -->

> **Thinking**
>
> Each person has up to $10^4$ slots; checking every pair is $O(mn)$. After sorting by start, an overlap can only involve the two current slots: start is the max of starts, end the min of ends.
>
> If the overlap is long enough, we return the earliest such interval. Otherwise the slot that ends first cannot form an earlier feasible overlap with later slots of the other person, so we advance that pointer.
>
> Sorting walks candidates in time order; two pointers discard a hopeless slot each step, for a linear number of comparisons.

<!-- thinking:end -->

We can sort the free time intervals of both people, then use two pointers to traverse the two arrays and find the intersection of the free time intervals of both people. If the length of the intersection is greater than or equal to `duration`, return the start time of the intersection and the start time plus `duration`. Otherwise, if the end time of the first person's free time interval is less than the end time of the second person's free time interval, move the first person's pointer; otherwise, move the second person's pointer. Continue traversing until a suitable time interval is found or the traversal ends.

The time complexity is $O(m \times \log m + n \times \log n)$, and the space complexity is $O(\log m + \log n)$. Here, $m$ and $n$ are the lengths of the two arrays, respectively.

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
