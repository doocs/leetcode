---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3074.Apple%20Redistribution%20into%20Boxes/README.md
rating: 1197
source: 第 388 场周赛 Q1
tags:
    - 贪心
    - 数组
    - 排序
---

# [3074. 重新分装苹果](https://leetcode.cn/problems/apple-redistribution-into-boxes)

[English Version](/solution/3000-3099/3074.Apple%20Redistribution%20into%20Boxes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的数组 <code>apple</code> 和另一个长度为 <code>m</code> 的数组 <code>capacity</code> 。</p>

<p>一共有 <code>n</code> 个包裹，其中第 <code>i</code> 个包裹中装着 <code>apple[i]</code> 个苹果。同时，还有 <code>m</code> 个箱子，第 <code>i</code> 个箱子的容量为 <code>capacity[i]</code> 个苹果。</p>

<p>请你选择一些箱子来将这 <code>n</code> 个包裹中的苹果重新分装到箱子中，返回你需要选择的箱子的<strong> 最小</strong> 数量。</p>

<p><strong>注意</strong>，同一个包裹中的苹果可以分装到不同的箱子中。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>apple = [1,3,2], capacity = [4,3,1,5,2]
<strong>输出：</strong>2
<strong>解释：</strong>使用容量为 4 和 5 的箱子。
总容量大于或等于苹果的总数，所以可以完成重新分装。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>apple = [5,5,5], capacity = [2,4,2,7]
<strong>输出：</strong>4
<strong>解释：</strong>需要使用所有箱子。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == apple.length &lt;= 50</code></li>
	<li><code>1 &lt;= m == capacity.length &lt;= 50</code></li>
	<li><code>1 &lt;= apple[i], capacity[i] &lt;= 50</code></li>
	<li>输入数据保证可以将包裹中的苹果重新分装到箱子中。</li>
</ul>

## 解法

### 方法一：贪心 + 排序

为了使得需要的箱子数量尽可能少，我们应该优先使用容量大的箱子。因此，我们可以对箱子按照容量从大到小排序，然后依次使用箱子，直到所有的苹果都被分装完，返回此时使用的箱子数量。

时间复杂度 $O(m \times \log m + n)$，空间复杂度 $O(\log m)$。其中 $m$ 和 $n$ 分别是数组 `capacity` 和 `apple` 的长度。

<!-- tabs:start -->

```python
class Solution:
    def minimumBoxes(self, apple: List[int], capacity: List[int]) -> int:
        capacity.sort(reverse=True)
        s = sum(apple)
        for i, c in enumerate(capacity, 1):
            s -= c
            if s <= 0:
                return i
```

```java
class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        Arrays.sort(capacity);
        int s = 0;
        for (int x : apple) {
            s += x;
        }
        for (int i = 1, n = capacity.length;; ++i) {
            s -= capacity[n - i];
            if (s <= 0) {
                return i;
            }
        }
    }
}
```

```cpp
class Solution {
public:
    int minimumBoxes(vector<int>& apple, vector<int>& capacity) {
        sort(capacity.rbegin(), capacity.rend());
        int s = accumulate(apple.begin(), apple.end(), 0);
        for (int i = 1;; ++i) {
            s -= capacity[i - 1];
            if (s <= 0) {
                return i;
            }
        }
    }
};
```

```go
func minimumBoxes(apple []int, capacity []int) int {
	sort.Ints(capacity)
	s := 0
	for _, x := range apple {
		s += x
	}
	for i := 1; ; i++ {
		s -= capacity[len(capacity)-i]
		if s <= 0 {
			return i
		}
	}
}
```

```ts
function minimumBoxes(apple: number[], capacity: number[]): number {
    capacity.sort((a, b) => b - a);
    let s = apple.reduce((acc, cur) => acc + cur, 0);
    for (let i = 1; ; ++i) {
        s -= capacity[i - 1];
        if (s <= 0) {
            return i;
        }
    }
}
```

<!-- tabs:end -->

<!-- end -->
