# [1561. 你可以获得的最大硬币数目](https://leetcode.cn/problems/maximum-number-of-coins-you-can-get)

[English Version](/solution/1500-1599/1561.Maximum%20Number%20of%20Coins%20You%20Can%20Get/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有 3n 堆数目不一的硬币，你和你的朋友们打算按以下方式分硬币：</p>

<ul>
	<li>每一轮中，你将会选出 <strong>任意</strong> 3 堆硬币（不一定连续）。</li>
	<li>Alice 将会取走硬币数量最多的那一堆。</li>
	<li>你将会取走硬币数量第二多的那一堆。</li>
	<li>Bob 将会取走最后一堆。</li>
	<li>重复这个过程，直到没有更多硬币。</li>
</ul>

<p>给你一个整数数组 <code>piles</code> ，其中 <code>piles[i]</code> 是第 <code>i</code> 堆中硬币的数目。</p>

<p>返回你可以获得的最大硬币数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>piles = [2,4,1,2,7,8]
<strong>输出：</strong>9
<strong>解释：</strong>选出 (2, 7, 8) ，Alice 取走 8 枚硬币的那堆，你取走 <strong>7</strong> 枚硬币的那堆，Bob 取走最后一堆。
选出 (1, 2, 4) , Alice 取走 4 枚硬币的那堆，你取走 <strong>2</strong> 枚硬币的那堆，Bob 取走最后一堆。
你可以获得的最大硬币数目：7 + 2 = 9.
考虑另外一种情况，如果选出的是 (1, <strong>2</strong>, 8) 和 (2, <strong>4</strong>, 7) ，你就只能得到 2 + 4 = 6 枚硬币，这不是最优解。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>piles = [2,4,5]
<strong>输出：</strong>4
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>piles = [9,8,7,6,5,1,2,3,4]
<strong>输出：</strong>18
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= piles.length &lt;= 10^5</code></li>
	<li><code>piles.length % 3 == 0</code></li>
	<li><code>1 &lt;= piles[i] &lt;= 10^4</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

Bob 取走最小的 1/3，剩余的硬币堆由 Alice 和我按硬币数从高到低依次取走每一堆。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxCoins(self, piles: List[int]) -> int:
        piles.sort()
        return sum(piles[-2 : len(piles) // 3 - 1 : -2])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int ans = 0;
        for (int i = piles.length - 2; i >= piles.length / 3; i -= 2) {
            ans += piles[i];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxCoins(vector<int>& piles) {
        sort(piles.begin(), piles.end());
        int ans = 0;
        for (int i = piles.size() - 2; i >= (int)piles.size() / 3; i -= 2) ans += piles[i];
        return ans;
    }
};
```

### **Go**

```go
func maxCoins(piles []int) int {
	sort.Ints(piles)
	ans, n := 0, len(piles)
	for i := n - 2; i >= n/3; i -= 2 {
		ans += piles[i]
	}
	return ans
}
```

### **TypeScript**

```ts
function maxCoins(piles: number[]): number {
    piles.sort((a, b) => a - b);
    const n = piles.length;
    let ans = 0;
    for (let i = 1; i <= Math.floor(n / 3); i++) {
        ans += piles[n - 2 * i];
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_coins(mut piles: Vec<i32>) -> i32 {
        piles.sort();
        let n = piles.len();
        let mut ans = 0;
        for i in 1..=n / 3 {
            ans += piles[n - 2 * i];
        }
        ans
    }
}
```

### **C**

```c
int cmp(const void *a, const void *b) {
    return *(int *) a - *(int *) b;
}

int maxCoins(int *piles, int pilesSize) {
    qsort(piles, pilesSize, sizeof(int), cmp);
    int ans = 0;
    for (int i = 1; i <= pilesSize / 3; i++) {
        ans += piles[pilesSize - 2 * i];
    };
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
