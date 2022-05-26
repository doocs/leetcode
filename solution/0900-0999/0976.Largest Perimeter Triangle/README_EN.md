# [976. Largest Perimeter Triangle](https://leetcode.com/problems/largest-perimeter-triangle)

[中文文档](/solution/0900-0999/0976.Largest%20Perimeter%20Triangle/README.md)

## Description

<p>Given an integer array <code>nums</code>, return <em>the largest perimeter of a triangle with a non-zero area, formed from three of these lengths</em>. If it is impossible to form any triangle of a non-zero area, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,2]
<strong>Output:</strong> 5
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,1]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

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
