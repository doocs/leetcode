# [605. Can Place Flowers](https://leetcode.com/problems/can-place-flowers)

[中文文档](/solution/0600-0699/0605.Can%20Place%20Flowers/README.md)

<!-- tags:Greedy,Array -->

<!-- difficulty:Easy -->

## Description

<p>You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in <strong>adjacent</strong> plots.</p>

<p>Given an integer array <code>flowerbed</code> containing <code>0</code>&#39;s and <code>1</code>&#39;s, where <code>0</code> means empty and <code>1</code> means not empty, and an integer <code>n</code>, return <code>true</code>&nbsp;<em>if</em> <code>n</code> <em>new flowers can be planted in the</em> <code>flowerbed</code> <em>without violating the no-adjacent-flowers rule and</em> <code>false</code> <em>otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> flowerbed = [1,0,0,0,1], n = 1
<strong>Output:</strong> true
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> flowerbed = [1,0,0,0,1], n = 2
<strong>Output:</strong> false
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= flowerbed.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>flowerbed[i]</code> is <code>0</code> or <code>1</code>.</li>
	<li>There are no two adjacent flowers in <code>flowerbed</code>.</li>
	<li><code>0 &lt;= n &lt;= flowerbed.length</code></li>
</ul>

## Solutions

### Solution 1: Greedy

We directly traverse the array $flowerbed$. For each position $i$, if $flowerbed[i]=0$ and its adjacent positions on the left and right are also $0$, then we can plant a flower at this position. Otherwise, we cannot. Finally, we count the number of flowers that can be planted. If it is not less than $n$, we return $true$, otherwise we return $false$.

The time complexity is $O(n)$, where $n$ is the length of the array $flowerbed$. We only need to traverse the array once. The space complexity is $O(1)$.

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
