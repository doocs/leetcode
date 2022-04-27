# [976. 三角形的最大周长](https://leetcode-cn.com/problems/largest-perimeter-triangle)

[English Version](/solution/0900-0999/0976.Largest%20Perimeter%20Triangle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定由一些正数（代表长度）组成的数组 <code>nums</code>&nbsp;，返回 <em>由其中三个长度组成的、<strong>面积不为零</strong>的三角形的最大周长</em>&nbsp;。如果不能形成任何面积不为零的三角形，返回&nbsp;<code>0</code>。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,1,2]
<strong>输出：</strong>5
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,1]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

> 三角形由三条边组成，且满足 <var>C</var> >= <var>B</var> && <var>C</var> >= <var>A</var> && <var>C</var> < <var>A</var> + <var>B</var>

贪心策略，尽可能使用长边来组成三角形。

1. 对 `nums` 排序（从大到小）。
2. 遍历 `nums`，以三个元素一组，进行条件判断，如滑动窗口一般。
3. 当找到满足条件的三个元素时直接返回即可。
4. 否则，在遍历结束时返回 0。

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
    int largestPerimeter(vector<int>& A) {
        priority_queue<int> q(A.begin(), A.end()) ; // 大顶堆

        int a, b, c ;
        b = q.top() ;
        q.pop() ;
        c = q.top() ;
        q.pop() ;
        while ( !q.empty() )
        {
            a = b ;
            b = c ;
            c = q.top() ;
            q.pop() ;
            if ( b + c > a )
                return a + b + c ;
        }
        return 0 ;
    }
};
```

### **TypeScript**

```ts
function largestPerimeter(nums: number[]): number {
    const n = nums.length;
    nums.sort((a, b) => b - a);
    for (let i = 2; i < n; i++) {
        const [a, b, c] = [nums[i - 2], nums[i - 1], nums[i]];
        if (a < b + c) {
            return a + b + c;
        }
    }
    return 0;
}
```

### **Rust**

```rust
impl Solution {
    pub fn largest_perimeter(mut nums: Vec<i32>) -> i32 {
        let n = nums.len();
        nums.sort_unstable_by(|a, b| b.cmp(&a));
        for i in 2..n {
            let (a, b, c) = (nums[i - 2], nums[i - 1], nums[i]);
            if a < b + c {
                return a + b + c;
            }
        }
        0
    }
}
```

### **...**

```

```

<!-- tabs:end -->
