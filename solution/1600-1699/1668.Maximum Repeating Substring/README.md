# [1668. 最大重复子字符串](https://leetcode.cn/problems/maximum-repeating-substring)

[English Version](/solution/1600-1699/1668.Maximum%20Repeating%20Substring/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>sequence</code> ，如果字符串 <code>word</code> 连续重复 <code>k</code> 次形成的字符串是 <code>sequence</code> 的一个子字符串，那么单词 <code>word</code> 的 <strong>重复值为 <code>k</code></strong><strong> </strong>。单词 <code>word</code> 的 <strong>最</strong><strong>大重复值</strong> 是单词 <code>word</code> 在 <code>sequence</code> 中最大的重复值。如果 <code>word</code> 不是 <code>sequence</code> 的子串，那么重复值 <code>k</code> 为 <code>0</code> 。</p>

<p>给你一个字符串 <code>sequence</code> 和 <code>word</code> ，请你返回 <strong>最大重复值 <code>k</code> </strong>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>sequence = "ababc", word = "ab"
<b>输出：</b>2
<strong>解释：</strong>"abab" 是 "<strong>abab</strong>c" 的子字符串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>sequence = "ababc", word = "ba"
<b>输出：</b>1
<strong>解释：</strong>"ba" 是 "a<strong>ba</strong>bc" 的子字符串，但 "baba" 不是 "ababc" 的子字符串。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>sequence = "ababc", word = "ac"
<b>输出：</b>0
<strong>解释：</strong>"ac" 不是 "ababc" 的子字符串。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= sequence.length <= 100</code></li>
	<li><code>1 <= word.length <= 100</code></li>
	<li><code>sequence</code> 和 <code>word</code> 都只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **C++**

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int x) {
        int n = nums.size();
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int target = sum - x;
        int res = -1;
        int l = 0;
        int r = 0;
        sum = 0;
        while (r < n) {
            sum += nums[r++];
            while (sum > target && l < n) {
                sum -= nums[l++];
            }
            if (sum == target) {
                res = max(res, r - l);
            }
        }

        if (res == -1) {
            return res;
        }
        return n - res;
    }
};
```

### **TypeScript**

```ts
function minOperations(nums: number[], x: number): number {
    const n = nums.length;
    const target = nums.reduce((r, v) => r + v) - x;

    let l = 0;
    let r = 0;
    let sum = 0;
    let max = -1;
    while (r < n) {
        sum += nums[r++];
        while (sum > target && l < r) {
            sum -= nums[l++];
        }

        if (sum === target) {
            max = Math.max(max, r - l);
        }
    }

    if (max === -1) {
        return max;
    }
    return n - max;
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_operations(nums: Vec<i32>, x: i32) -> i32 {
        let n = nums.len();
        let target = nums.iter().sum::<i32>() - x;


        let (mut l, mut r) = (0, 0);
        let (mut sum, mut max) = (0, -1);
        while r < n {
            sum += nums[r];
            r += 1;
            while sum > target && l < r {
                sum -= nums[l];
                l += 1;
            }


            if sum == target {
                max = max.max((r - l) as i32);
            }
        }


        if max == -1 {
            return max;
        }
        return n as i32 - max;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
