---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0605.Can%20Place%20Flowers/README.md
tags:
    - 贪心
    - 数组
---

# [605. 种花问题](https://leetcode.cn/problems/can-place-flowers)

[English Version](/solution/0600-0699/0605.Can%20Place%20Flowers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。</p>

<p>给你一个整数数组&nbsp;<code>flowerbed</code> 表示花坛，由若干 <code>0</code> 和 <code>1</code> 组成，其中 <code>0</code> 表示没种植花，<code>1</code> 表示种植了花。另有一个数&nbsp;<code>n</code><strong> </strong>，能否在不打破种植规则的情况下种入&nbsp;<code>n</code><strong>&nbsp;</strong>朵花？能则返回 <code>true</code> ，不能则返回 <code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>flowerbed = [1,0,0,0,1], n = 1
<strong>输出：</strong>true
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>flowerbed = [1,0,0,0,1], n = 2
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= flowerbed.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>flowerbed[i]</code> 为 <code>0</code> 或 <code>1</code></li>
	<li><code>flowerbed</code> 中不存在相邻的两朵花</li>
	<li><code>0 &lt;= n &lt;= flowerbed.length</code></li>
</ul>

## 解法

### 方法一：贪心

我们直接遍历数组 $flowerbed$，对于每个位置 $i$，如果 $flowerbed[i]=0$，并且其左右相邻位置都为 $0$，则我们可以在该位置种花，否则不能。最后我们统计可以种下的花的数量，如果其不小于 $n$，则返回 $true$，否则返回 $false$。

时间复杂度 $O(n)$，其中 $n$ 是数组 $flowerbed$ 的长度。我们只需要遍历数组一次。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def canPlaceFlowers(self, flowerbed: List[int], n: int) -> bool:
        flowerbed = [0] + flowerbed + [0]
        for i in range(1, len(flowerbed) - 1):
            if sum(flowerbed[i - 1 : i + 2]) == 0:
                flowerbed[i] = 1
                n -= 1
        return n <= 0
```

```java
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int m = flowerbed.length;
        for (int i = 0; i < m; ++i) {
            int l = i == 0 ? 0 : flowerbed[i - 1];
            int r = i == m - 1 ? 0 : flowerbed[i + 1];
            if (l + flowerbed[i] + r == 0) {
                flowerbed[i] = 1;
                --n;
            }
        }
        return n <= 0;
    }
}
```

```cpp
class Solution {
public:
    bool canPlaceFlowers(vector<int>& flowerbed, int n) {
        int m = flowerbed.size();
        for (int i = 0; i < m; ++i) {
            int l = i == 0 ? 0 : flowerbed[i - 1];
            int r = i == m - 1 ? 0 : flowerbed[i + 1];
            if (l + flowerbed[i] + r == 0) {
                flowerbed[i] = 1;
                --n;
            }
        }
        return n <= 0;
    }
};
```

```go
func canPlaceFlowers(flowerbed []int, n int) bool {
	m := len(flowerbed)
	for i, v := range flowerbed {
		l, r := 0, 0
		if i > 0 {
			l = flowerbed[i-1]
		}
		if i < m-1 {
			r = flowerbed[i+1]
		}
		if l+v+r == 0 {
			flowerbed[i] = 1
			n--
		}
	}
	return n <= 0
}
```

```ts
function canPlaceFlowers(flowerbed: number[], n: number): boolean {
    const m = flowerbed.length;
    for (let i = 0; i < m; ++i) {
        const l = i === 0 ? 0 : flowerbed[i - 1];
        const r = i === m - 1 ? 0 : flowerbed[i + 1];
        if (l + flowerbed[i] + r === 0) {
            flowerbed[i] = 1;
            --n;
        }
    }
    return n <= 0;
}
```

```rust
impl Solution {
    pub fn can_place_flowers(flowerbed: Vec<i32>, n: i32) -> bool {
        let (mut flowers, mut cnt) = (vec![0], 0);
        flowers.append(&mut flowerbed.clone());
        flowers.push(0);

        for i in 1..flowers.len() - 1 {
            let (l, r) = (flowers[i - 1], flowers[i + 1]);
            if l + flowers[i] + r == 0 {
                flowers[i] = 1;
                cnt += 1;
            }
        }
        cnt >= n
    }
}
```

```php
class Solution {
    /**
     * @param Integer[] $flowerbed
     * @param Integer $n
     * @return Boolean
     */
    function canPlaceFlowers($flowerbed, $n) {
        array_push($flowerbed, 0);
        array_unshift($flowerbed, 0);
        for ($i = 1; $i < count($flowerbed) - 1; $i++) {
            if ($flowerbed[$i] === 0) {
                if ($flowerbed[$i - 1] === 0 && $flowerbed[$i + 1] === 0) {
                    $flowerbed[$i] = 1;
                    $n--;
                }
            }
        }
        return $n <= 0;
    }
}
```

<!-- tabs:end -->

<!-- end -->
