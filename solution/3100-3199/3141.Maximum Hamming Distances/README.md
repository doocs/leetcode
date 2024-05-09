# [3141. 最大海明距离 🔒](https://leetcode.cn/problems/maximum-hamming-distances)

[English Version](/solution/3100-3199/3141.Maximum%20Hamming%20Distances/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>m</code>，每个元素&nbsp;<code>nums[i]</code>&nbsp;满足&nbsp;<code>0 &lt;= nums[i] &lt; 2<sup>m</sup></code>，返回数组&nbsp;<code>answer</code>。<code>answer</code>&nbsp;数组应该与&nbsp;<code>nums</code>&nbsp; 有相同的长度，每个元素&nbsp;<code>answer[i]</code>&nbsp;表示&nbsp;<code>nums[i]</code>&nbsp;和数组中其它任何元素&nbsp;<code>nums[j]</code>&nbsp;的最大 <strong>海明距离</strong>。</p>

<p>两个二进制整数之间的&nbsp;<strong>海明距离</strong> 定义为对应位上二进制位不同的数量（如果需要，添加前置零）。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [9,12,9,11], m = 4</span></p>

<p><strong>输出：</strong><span class="example-io">[2,3,2,3]</span></p>

<p><strong>解释：</strong></p>

<p>二进制表示为&nbsp;<code>nums = [1001,1100,1001,1011]</code>。</p>

<p>每个下标的最大海明距离为：</p>

<ul>
	<li><code>nums[0]</code>：1001 与 1100 距离为 2。</li>
	<li><code>nums[1]</code>：1100 与 1011 距离为&nbsp;3。</li>
	<li><code>nums[2]</code>：1001 与 1100 距离为&nbsp;2。</li>
	<li><code>nums[3]</code>：1011 与 1100 距离为&nbsp;3。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [3,4,6,10], m = 4</span></p>

<p><strong>输出：</strong><span class="example-io">[3,3,2,3]</span></p>

<p><strong>解释：</strong></p>

<p>二进制表示为 <code>nums = [0011,0100,0110,1010]</code>。</p>

<p>每个下标的最大海明距离为：</p>

<ul>
	<li><code>nums[0]</code>：0011 与 0100 距离为 3。</li>
	<li><code>nums[1]</code>：0100 与 0011 距离为 3。</li>
	<li><code>nums[2]</code>：0110 与 1010 距离为 2。</li>
	<li><code>nums[3]</code>：1010 与 0100 距离为 3。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m &lt;= 17</code></li>
	<li><code>2 &lt;= nums.length &lt;= 2<sup>m</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt; 2<sup>m</sup></code></li>
</ul>

## 解法

### 方法一：逆向思维 + BFS

题目需要我们求出数组每个元素和其他元素的最大海明距离，我们不妨换个角度思考，我们对每个元素取反，然后求出其到数组中其他元素的最小海明距离，那么 $m$ 减去这个最小海明距离就是我们要求的最大海明距离。

我们可以使用广度优先搜索来求出每个取反的元素到其他元素的最小海明距离。

具体步骤如下：

1. 初始化一个数组 $\text{dist}$，数组长度为 $2^m$，用来记录每个取反后的元素到其他元素的最小海明距离，初始时全部置为 $-1$。
2. 遍历数组 $\text{nums}$，将每个元素的取反值置为 $0$，并将其加入队列 $\text{q}$。
3. 从 $k = 1$ 开始，不断遍历队列 $\text{q}$，每次遍历时，将队列中的元素取出，然后对其进行 $m$ 次取反操作，将取反后的元素加入队列 $\text{t}$，并将其到原元素的最小海明距离置为 $k$。
4. 重复步骤 3，直到队列为空。

最后，我们遍历数组 $\text{nums}$，将每个元素取反后的值作为下标，从 $\text{dist}$ 数组中取出对应的最小海明距离，然后用 $m$ 减去这个值，就是我们要求的最大海明距离。

时间复杂度 $O(2^m)$，空间复杂度 $O(2^m)$。其中 $m$ 为题目给定的整数。

<!-- tabs:start -->

```python
class Solution:
    def maxHammingDistances(self, nums: List[int], m: int) -> List[int]:
        dist = [-1] * (1 << m)
        for x in nums:
            dist[x] = 0
        q = nums
        k = 1
        while q:
            t = []
            for x in q:
                for i in range(m):
                    y = x ^ (1 << i)
                    if dist[y] == -1:
                        t.append(y)
                        dist[y] = k
            q = t
            k += 1
        return [m - dist[x ^ ((1 << m) - 1)] for x in nums]
```

```java
class Solution {
    public int[] maxHammingDistances(int[] nums, int m) {
        int[] dist = new int[1 << m];
        Arrays.fill(dist, -1);
        Deque<Integer> q = new ArrayDeque<>();
        for (int x : nums) {
            dist[x] = 0;
            q.offer(x);
        }
        for (int k = 1; !q.isEmpty(); ++k) {
            for (int t = q.size(); t > 0; --t) {
                int x = q.poll();
                for (int i = 0; i < m; ++i) {
                    int y = x ^ (1 << i);
                    if (dist[y] == -1) {
                        q.offer(y);
                        dist[y] = k;
                    }
                }
            }
        }
        for (int i = 0; i < nums.length; ++i) {
            nums[i] = m - dist[nums[i] ^ ((1 << m) - 1)];
        }
        return nums;
    }
}
```

```cpp
class Solution {
public:
    vector<int> maxHammingDistances(vector<int>& nums, int m) {
        int dist[1 << m];
        memset(dist, -1, sizeof(dist));
        queue<int> q;
        for (int x : nums) {
            dist[x] = 0;
            q.push(x);
        }
        for (int k = 1; q.size(); ++k) {
            for (int t = q.size(); t; --t) {
                int x = q.front();
                q.pop();
                for (int i = 0; i < m; ++i) {
                    int y = x ^ (1 << i);
                    if (dist[y] == -1) {
                        dist[y] = k;
                        q.push(y);
                    }
                }
            }
        }
        for (int& x : nums) {
            x = m - dist[x ^ ((1 << m) - 1)];
        }
        return nums;
    }
};
```

```go
func maxHammingDistances(nums []int, m int) []int {
	dist := make([]int, 1<<m)
	for i := range dist {
		dist[i] = -1
	}
	q := []int{}
	for _, x := range nums {
		dist[x] = 0
		q = append(q, x)
	}
	for k := 1; len(q) > 0; k++ {
		t := []int{}
		for _, x := range q {
			for i := 0; i < m; i++ {
				y := x ^ (1 << i)
				if dist[y] == -1 {
					dist[y] = k
					t = append(t, y)
				}
			}
		}
		q = t
	}
	for i, x := range nums {
		nums[i] = m - dist[x^(1<<m-1)]
	}
	return nums
}
```

```ts
function maxHammingDistances(nums: number[], m: number): number[] {
    const dist: number[] = Array.from({ length: 1 << m }, () => -1);
    const q: number[] = [];
    for (const x of nums) {
        dist[x] = 0;
        q.push(x);
    }
    for (let k = 1; q.length; ++k) {
        const t: number[] = [];
        for (const x of q) {
            for (let i = 0; i < m; ++i) {
                const y = x ^ (1 << i);
                if (dist[y] === -1) {
                    dist[y] = k;
                    t.push(y);
                }
            }
        }
        q.splice(0, q.length, ...t);
    }
    for (let i = 0; i < nums.length; ++i) {
        nums[i] = m - dist[nums[i] ^ ((1 << m) - 1)];
    }
    return nums;
}
```

<!-- tabs:end -->

<!-- end -->
