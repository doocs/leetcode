---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2105.Watering%20Plants%20II/README.md
rating: 1507
source: 第 271 场周赛 Q3
tags:
    - 数组
    - 双指针
    - 模拟
---

<!-- problem:start -->

# [2105. 给植物浇水 II](https://leetcode.cn/problems/watering-plants-ii)

[English Version](/solution/2100-2199/2105.Watering%20Plants%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Alice 和 Bob 打算给花园里的 <code>n</code> 株植物浇水。植物排成一行，从左到右进行标记，编号从 <code>0</code> 到 <code>n - 1</code> 。其中，第 <code>i</code> 株植物的位置是 <code>x = i</code> 。</p>

<p>每一株植物都需要浇特定量的水。Alice 和 Bob 每人有一个水罐，<strong>最初是满的 </strong>。他们按下面描述的方式完成浇水：</p>

<ul>
	<li>&nbsp;Alice 按 <strong>从左到右</strong> 的顺序给植物浇水，从植物 <code>0</code> 开始。Bob 按 <strong>从右到左</strong> 的顺序给植物浇水，从植物 <code>n - 1</code> 开始。他们 <strong>同时</strong> 给植物浇水。</li>
	<li>无论需要多少水，为每株植物浇水所需的时间都是相同的。</li>
	<li>如果 Alice/Bob 水罐中的水足以 <strong>完全</strong> 灌溉植物，他们 <strong>必须</strong> 给植物浇水。否则，他们 <strong>首先</strong>（立即）重新装满罐子，然后给植物浇水。</li>
	<li>如果 Alice 和 Bob 到达同一株植物，那么当前水罐中水 <strong>更多</strong> 的人会给这株植物浇水。如果他俩水量相同，那么 Alice 会给这株植物浇水。</li>
</ul>

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>plants</code> ，数组由 <code>n</code> 个整数组成。其中，<code>plants[i]</code> 为第 <code>i</code> 株植物需要的水量。另有两个整数 <code>capacityA</code> 和&nbsp;<code>capacityB</code> 分别表示 Alice 和 Bob 水罐的容量。返回两人浇灌所有植物过程中重新灌满水罐的 <strong>次数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>plants = [2,2,3,3], capacityA = 5, capacityB = 5
<strong>输出：</strong>1
<strong>解释：</strong>
- 最初，Alice 和 Bob 的水罐中各有 5 单元水。
- Alice 给植物 0 浇水，Bob 给植物 3 浇水。
- Alice 和 Bob 现在分别剩下 3 单元和 2 单元水。
- Alice 有足够的水给植物 1 ，所以她直接浇水。Bob 的水不够给植物 2 ，所以他先重新装满水，再浇水。
所以，两人浇灌所有植物过程中重新灌满水罐的次数 = 0 + 0 + 1 + 0 = 1 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>plants = [2,2,3,3], capacityA = 3, capacityB = 4
<strong>输出：</strong>2
<strong>解释：</strong>
- 最初，Alice 的水罐中有 3 单元水，Bob 的水罐中有 4 单元水。
- Alice 给植物 0 浇水，Bob 给植物 3 浇水。
- Alice 和 Bob 现在都只有 1 单元水，并分别需要给植物 1 和植物 2 浇水。
- 由于他们的水量均不足以浇水，所以他们重新灌满水罐再进行浇水。
所以，两人浇灌所有植物过程中重新灌满水罐的次数 = 0 + 1 + 1 + 0 = 2 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>plants = [5], capacityA = 10, capacityB = 8
<strong>输出：</strong>0
<strong>解释：</strong>
- 只有一株植物
- Alice 的水罐有 10 单元水，Bob 的水罐有 8 单元水。因此 Alice 的水罐中水更多，她会给这株植物浇水。
所以，两人浇灌所有植物过程中重新灌满水罐的次数 = 0 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == plants.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= plants[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>max(plants[i]) &lt;= capacityA, capacityB &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针 + 模拟

我们用两个变量 $a$ 和 $b$ 分别表示 Alice 和 Bob 的水量，初始时 $a = \text{capacityA}$, $b = \text{capacityB}$。然后用两个指针 $i$ 和 $j$ 分别指向植物数组的头尾，然后模拟 Alice 和 Bob 从两端向中间浇水的过程。

当 $i < j$ 时，我们分别判断 Alice 和 Bob 的水量是否足够浇水，如果不够，我们就重新灌满水罐。然后更新 $a$ 和 $b$ 的水量，同时移动指针 $i$ 和 $j$。最后我们还需要判断 $i$ 和 $j$ 是否相等，如果相等，我们还需要判断 $\max(a, b)$ 是否小于植物的水量，如果小于，我们需要再次重新灌满水罐。

时间复杂度 $O(n)$，其中 $n$ 是植物数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minimumRefill(self, plants: List[int], capacityA: int, capacityB: int) -> int:
        a, b = capacityA, capacityB
        ans = 0
        i, j = 0, len(plants) - 1
        while i < j:
            if a < plants[i]:
                ans += 1
                a = capacityA
            a -= plants[i]
            if b < plants[j]:
                ans += 1
                b = capacityB
            b -= plants[j]
            i, j = i + 1, j - 1
        ans += i == j and max(a, b) < plants[i]
        return ans
```

```java
class Solution {
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int a = capacityA, b = capacityB;
        int ans = 0;
        int i = 0, j = plants.length - 1;
        for (; i < j; ++i, --j) {
            if (a < plants[i]) {
                ++ans;
                a = capacityA;
            }
            a -= plants[i];
            if (b < plants[j]) {
                ++ans;
                b = capacityB;
            }
            b -= plants[j];
        }
        ans += i == j && Math.max(a, b) < plants[i] ? 1 : 0;
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumRefill(vector<int>& plants, int capacityA, int capacityB) {
        int a = capacityA, b = capacityB;
        int ans = 0;
        int i = 0, j = plants.size() - 1;
        for (; i < j; ++i, --j) {
            if (a < plants[i]) {
                ++ans;
                a = capacityA;
            }
            a -= plants[i];
            if (b < plants[j]) {
                ++ans;
                b = capacityB;
            }
            b -= plants[j];
        }
        ans += i == j && max(a, b) < plants[i];
        return ans;
    }
};
```

```go
func minimumRefill(plants []int, capacityA int, capacityB int) (ans int) {
	a, b := capacityA, capacityB
	i, j := 0, len(plants)-1
	for ; i < j; i, j = i+1, j-1 {
		if a < plants[i] {
			ans++
			a = capacityA
		}
		a -= plants[i]
		if b < plants[j] {
			ans++
			b = capacityB
		}
		b -= plants[j]
	}
	if i == j && max(a, b) < plants[i] {
		ans++
	}
	return
}
```

```ts
function minimumRefill(plants: number[], capacityA: number, capacityB: number): number {
    let [a, b] = [capacityA, capacityB];
    let ans = 0;
    let [i, j] = [0, plants.length - 1];
    for (; i < j; ++i, --j) {
        if (a < plants[i]) {
            ++ans;
            a = capacityA;
        }
        a -= plants[i];
        if (b < plants[j]) {
            ++ans;
            b = capacityB;
        }
        b -= plants[j];
    }
    ans += i === j && Math.max(a, b) < plants[i] ? 1 : 0;
    return ans;
}
```

```rust
impl Solution {
    pub fn minimum_refill(plants: Vec<i32>, capacity_a: i32, capacity_b: i32) -> i32 {
        let mut a = capacity_a;
        let mut b = capacity_b;
        let mut ans = 0;
        let mut i = 0;
        let mut j = plants.len() - 1;

        while i < j {
            if a < plants[i] {
                ans += 1;
                a = capacity_a;
            }
            a -= plants[i];

            if b < plants[j] {
                ans += 1;
                b = capacity_b;
            }
            b -= plants[j];

            i += 1;
            j -= 1;
        }

        if i == j && a.max(b) < plants[i] {
            ans += 1;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
