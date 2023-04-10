# [643. 子数组最大平均数 I](https://leetcode.cn/problems/maximum-average-subarray-i)

[English Version](/solution/0600-0699/0643.Maximum%20Average%20Subarray%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由 <code>n</code> 个元素组成的整数数组 <code>nums</code> 和一个整数 <code>k</code> 。</p>

<p>请你找出平均数最大且 <strong>长度为 <code>k</code></strong> 的连续子数组，并输出该最大平均数。</p>

<p>任何误差小于 <code>10<sup>-5</sup></code> 的答案都将被视为正确答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,12,-5,-6,50,3], k = 4
<strong>输出：</strong>12.75
<strong>解释：</strong>最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [5], k = 1
<strong>输出：</strong>5.00000
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= k &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

滑动窗口。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findMaxAverage(self, nums: List[int], k: int) -> float:
        s = sum(nums[:k])
        ans = s
        for i in range(k, len(nums)):
            s += nums[i] - nums[i - k]
            ans = max(ans, s)
        return ans / k
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int s = 0;
        for (int i = 0; i < k; ++i) {
            s += nums[i];
        }
        int ans = s;
        for (int i = k; i < nums.length; ++i) {
            s += (nums[i] - nums[i - k]);
            ans = Math.max(ans, s);
        }
        return ans * 1.0 / k;
    }
}
```

### **TypeScript**

```ts
function findMaxAverage(nums: number[], k: number): number {
    let n = nums.length;
    let ans = 0;
    let sum = 0;
    // 前k
    for (let i = 0; i < k; i++) {
        sum += nums[i];
    }
    ans = sum;
    for (let i = k; i < n; i++) {
        sum += nums[i] - nums[i - k];
        ans = Math.max(ans, sum);
    }
    return ans / k;
}
```

### **Rust**

```rust
impl Solution {
    pub fn find_max_average(nums: Vec<i32>, k: i32) -> f64 {
        let k = k as usize;
        let n = nums.len();
        let mut sum = nums.iter().take(k).sum::<i32>();
        let mut max = sum;
        for i in k..n {
            sum += nums[i] - nums[i - k];
            max = max.max(sum);
        }
        f64::from(max) / f64::from(k as i32)
    }
}
```

### **PHP**

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $k
     * @return Float
     */
    function findMaxAverage($nums, $k) {
        $sum = 0;
        for ($i = 0; $i < $k; $i++) {
            $sum += $nums[$i];
        }
        $max = $sum;
        for ($j = $k; $j < count($nums); $j++) {
            $sum = $sum - $nums[$j - $k] + $nums[$j];
            $max = max($max, $sum);
        }
        return $max / $k;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
