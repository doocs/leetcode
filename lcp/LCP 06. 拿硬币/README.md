---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2006.%20%E6%8B%BF%E7%A1%AC%E5%B8%81/README.md
---

<!-- problem:start -->

# [LCP 06. 拿硬币](https://leetcode.cn/problems/na-ying-bi)

## 题目描述

<!-- description:start -->

<p>桌上有 <code>n</code> 堆力扣币，每堆的数量保存在数组 <code>coins</code> 中。我们每次可以选择任意一堆，拿走其中的一枚或者两枚，求拿完所有力扣币的最少次数。</p>

<p><strong>示例 1：</strong></p>

<blockquote>
<p>输入：<code>[4,2,1]</code></p>

<p>输出：<code>4</code></p>

<p>解释：第一堆力扣币最少需要拿 2 次，第二堆最少需要拿 1 次，第三堆最少需要拿 1 次，总共 4 次即可拿完。</p>
</blockquote>

<p><strong>示例 2：</strong></p>

<blockquote>
<p>输入：<code>[2,3,10]</code></p>

<p>输出：<code>8</code></p>
</blockquote>

<p><strong>限制：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 4</code></li>
	<li><code>1 &lt;= coins[i] &lt;= 10</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

我们可以发现，每堆力扣币拿完的最少次数，等于该堆力扣币数量除以 $2$ 向上取整的结果之和。

因此，我们只需要遍历每堆力扣币 $x_i$，计算每堆力扣币拿完的最少次数 $\left \lceil x_i/2 \right \rceil$，然后累加即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $coins$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCount(self, coins: List[int]) -> int:
        return sum((x + 1) >> 1 for x in coins)
```

#### Java

```java
class Solution {
    public int minCount(int[] coins) {
        int ans = 0;
        for (int x : coins) {
            ans += (x + 1) >> 1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minCount(vector<int>& coins) {
        int ans = 0;
        for (int x : coins) {
            ans += (x + 1) >> 1;
        }
        return ans;
    }
};
```

#### Go

```go
func minCount(coins []int) (ans int) {
	for _, x := range coins {
		ans += (x + 1) >> 1
	}
	return
}
```

#### TypeScript

```ts
function minCount(coins: number[]): number {
    let ans = 0;
    for (const x of coins) {
        ans += (x + 1) >> 1;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_count(coins: Vec<i32>) -> i32 {
        coins.iter().map(|&x| (x + 1) >> 1).sum::<i32>()
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $coins
     * @return Integer
     */
    function minCount($coins) {
        $ans = 0;
        foreach ($coins as $x) {
            $ans += $x + 1 >> 1;
        }
        return $ans;
    }
}
```

#### C

```c
int minCount(int* coins, int coinsSize) {
    int ans = 0;
    for (int i = 0; i < coinsSize; ++i) {
        ans += (coins[i] + 1) >> 1;
    }
    return ans;
}
```

#### Swift

```swift
class Solution {
    func minCount(_ coins: [Int]) -> Int {
        var ans = 0
        for x in coins {
            ans += (x + 1) >> 1
        }
        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
