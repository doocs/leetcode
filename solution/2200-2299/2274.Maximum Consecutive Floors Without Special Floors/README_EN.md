# [2274. Maximum Consecutive Floors Without Special Floors](https://leetcode.com/problems/maximum-consecutive-floors-without-special-floors)

[中文文档](/solution/2200-2299/2274.Maximum%20Consecutive%20Floors%20Without%20Special%20Floors/README.md)

## Description

<p>Alice manages a company and has rented some floors of a building as office space. Alice has decided some of these floors should be <strong>special floors</strong>, used for relaxation only.</p>

<p>You are given two integers <code>bottom</code> and <code>top</code>, which denote that Alice has rented all the floors from <code>bottom</code> to <code>top</code> (<strong>inclusive</strong>). You are also given the integer array <code>special</code>, where <code>special[i]</code> denotes a special floor that Alice has designated for relaxation.</p>

<p>Return <em>the <strong>maximum</strong> number of consecutive floors without a special floor</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> bottom = 2, top = 9, special = [4,6]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The following are the ranges (inclusive) of consecutive floors without a special floor:
- (2, 3) with a total amount of 2 floors.
- (5, 5) with a total amount of 1 floor.
- (7, 9) with a total amount of 3 floors.
Therefore, we return the maximum number which is 3 floors.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> bottom = 6, top = 8, special = [7,6,8]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Every floor rented is a special floor, so we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= special.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= bottom &lt;= special[i] &lt;= top &lt;= 10<sup>9</sup></code></li>
	<li>All the values of <code>special</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxConsecutive(self, bottom: int, top: int, special: List[int]) -> int:
        special.sort()
        ans = max(special[0] - bottom, top - special[-1])
        for i in range(1, len(special)):
            ans = max(ans, special[i] - special[i - 1] - 1)
        return ans
```

### **Java**

```java
class Solution {
    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int n = special.length;
        int ans = Math.max(special[0] - bottom, top - special[n - 1]);
        for (int i = 1; i < n; ++i) {
            ans = Math.max(ans, special[i] - special[i - 1] - 1);
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function maxConsecutive(
    bottom: number,
    top: number,
    special: number[],
): number {
    let nums = special.slice().sort((a, b) => a - b);
    nums.unshift(bottom - 1);
    nums.push(top + 1);
    let ans = 0;
    const n = nums.length;
    for (let i = 1; i < n; i++) {
        ans = Math.max(ans, nums[i] - nums[i - 1] - 1);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
