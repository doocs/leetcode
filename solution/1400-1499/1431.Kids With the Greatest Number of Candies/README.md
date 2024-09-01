---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1431.Kids%20With%20the%20Greatest%20Number%20of%20Candies/README.md
rating: 1176
source: 第 25 场双周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [1431. 拥有最多糖果的孩子](https://leetcode.cn/problems/kids-with-the-greatest-number-of-candies)

[English Version](/solution/1400-1499/1431.Kids%20With%20the%20Greatest%20Number%20of%20Candies/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有&nbsp;<code>n</code>&nbsp;个有糖果的孩子。给你一个数组&nbsp;<code>candies</code>，其中&nbsp;<code>candies[i]</code>&nbsp;代表第 <code>i</code> 个孩子拥有的糖果数目，和一个整数&nbsp;<code>extraCandies</code>&nbsp;表示你所有的额外糖果的数量。</p>

<p>返回一个长度为&nbsp;<code>n</code>&nbsp;的布尔数组 <code>result</code>，如果把所有的&nbsp;<code>extraCandies</code>&nbsp;给第&nbsp;<code>i</code>&nbsp;个孩子之后，他会拥有所有孩子中&nbsp;<strong>最多&nbsp;</strong>的糖果，那么&nbsp;<code>result[i]</code>&nbsp;为&nbsp;<code>true</code>，否则为 <code>false</code>。</p>

<p>注意，允许有多个孩子同时拥有 <strong>最多</strong>&nbsp;的糖果数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>candies = [2,3,5,1,3], extraCandies = 3
<strong>输出：</strong>[true,true,true,false,true] 
<strong>解释：</strong>如果你把额外的糖果全部给：
孩子 1，将有 2 + 3 = 5 个糖果，是孩子中最多的。
孩子 2，将有 3 + 3 = 6 个糖果，是孩子中最多的。
孩子 3，将有 5 + 3 = 8 个糖果，是孩子中最多的。
孩子 4，将有 1 + 3 = 4 个糖果，是孩子中最多的。
孩子 5，将有 3 + 3 = 6 个糖果，是孩子中最多的。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>candies = [4,2,1,1,2], extraCandies = 1
<strong>输出：</strong>[true,false,false,false,false] 
<strong>解释：</strong>只有 1 个额外糖果，所以不管额外糖果给谁，只有孩子 1 可以成为拥有糖果最多的孩子。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>candies = [12,1,12], extraCandies = 10
<strong>输出：</strong>[true,false,true]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == candies.length</code></li>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= candies[i] &lt;= 100</code></li>
	<li><code>1 &lt;= extraCandies &lt;= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kidsWithCandies(self, candies: List[int], extraCandies: int) -> List[bool]:
        mx = max(candies)
        return [candy + extraCandies >= mx for candy in candies]
```

#### Java

```java
class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int mx = 0;
        for (int candy : candies) {
            mx = Math.max(mx, candy);
        }
        List<Boolean> res = new ArrayList<>();
        for (int candy : candies) {
            res.add(candy + extraCandies >= mx);
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<bool> kidsWithCandies(vector<int>& candies, int extraCandies) {
        int mx = *max_element(candies.begin(), candies.end());
        vector<bool> res;
        for (int candy : candies) {
            res.push_back(candy + extraCandies >= mx);
        }
        return res;
    }
};
```

#### Go

```go
func kidsWithCandies(candies []int, extraCandies int) (ans []bool) {
	mx := slices.Max(candies)
	for _, candy := range candies {
		ans = append(ans, candy+extraCandies >= mx)
	}
	return
}
```

#### TypeScript

```ts
function kidsWithCandies(candies: number[], extraCandies: number): boolean[] {
    const max = candies.reduce((r, v) => Math.max(r, v));
    return candies.map(v => v + extraCandies >= max);
}
```

#### Rust

```rust
impl Solution {
    pub fn kids_with_candies(candies: Vec<i32>, extra_candies: i32) -> Vec<bool> {
        let max = *candies.iter().max().unwrap();
        candies.iter().map(|v| v + extra_candies >= max).collect()
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $candies
     * @param Integer $extraCandies
     * @return Boolean[]
     */
    function kidsWithCandies($candies, $extraCandies) {
        $max = max($candies);
        $rs = [];
        for ($i = 0; $i < count($candies); $i++) {
            array_push($rs, $candies[$i] + $extraCandies >= $max);
        }
        return $rs;
    }
}
```

#### C

```c
#define max(a, b) (((a) > (b)) ? (a) : (b))
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
bool* kidsWithCandies(int* candies, int candiesSize, int extraCandies, int* returnSize) {
    int mx = 0;
    for (int i = 0; i < candiesSize; i++) {
        mx = max(mx, candies[i]);
    }
    bool* ans = malloc(candiesSize * sizeof(bool));
    for (int i = 0; i < candiesSize; i++) {
        ans[i] = candies[i] + extraCandies >= mx;
    }
    *returnSize = candiesSize;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
